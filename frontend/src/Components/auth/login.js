import React, { useState} from "react";
import './login.css';
import { Link, useNavigate } from "react-router-dom";
import doctor from "../images/logo.png";
import axios from "axios";
import { jwtDecode } from "jwt-decode"; //extract the role directly from the token.

function Login() {
    const [email,setEmail]= useState("");
    const [motDePasse,setMotDePasse] = useState("");
    const [errorMessage, setErrorMessage]= useState("");
    const navigate = useNavigate();

    const handleSubmit = async(e) =>{
        e.preventDefault();
        try{
            const res = await axios.post("http://localhost:8080/auth/login", {email,motDePasse});
            console.log(res.data);
            
            const { token} = res.data;
            if (typeof token !== 'string') {
                throw new Error("Invalid token: must be a string");
              }
            // Decode the JWT token to get the role
            const decodedToken = jwtDecode(token);
            const role = decodedToken.role;

            //!!! Save token or user info to local storage
            localStorage.setItem("token", token);
            localStorage.setItem("role", role);
            console.log("Role from backend: ", role);
            
            if (role === "directeur") {
                navigate("/dashboard-directeur");
            } else if (role === "medecin") {
                navigate("/dashboard-medecin");
            } else if (role === "secretaire") {
                navigate("/dashboard-secretaire");
            } else if (role === "pharmacien") {
                navigate("/dashboard-pharmacien");
            }
             else {
                navigate("/dashboard-patient");
            }

        }catch(error){
            console.error(error);
            setErrorMessage("Invalide email ou mot de passe");
        }
    }


    return (
        <div className="login-container d-flex align-items-center justify-content-center">
            <div className="login-card d-flex">
                <div className="login-image-section d-flex align-items-center justify-content-center">
                    <img src={doctor} alt="doctor" className="login-image" />
                </div>
                <div className="login-form-section">
                    <form className="login-form" onSubmit={handleSubmit}>
                        <h3 className="text-center text-dark mb-4">Connexion</h3>
                        <div className="form-group mb-3">
                            <input type="email" className="form-control" id="email" aria-describedby="emailHelp" value={email} onChange={(e)=>setEmail(e.target.value)} placeholder="Enter email" />
                        </div>
                        <div className="form-group mb-3">
                            <input type="password" className="form-control" id="password" value={motDePasse} onChange={(e)=>setMotDePasse(e.target.value)} placeholder="Mot de passe"/>
                            {errorMessage && (<div className="text-danger">*
                                {errorMessage}
                            </div>)}
                            <small className="d-flex justify-content-center mt-2">
                                <Link to="/check">Mot de passe oublié?</Link>
                            </small>
                        </div>
                        <small className="d-flex justify-content-center mt-2">
                                <h6>
                                Vous n'avez pas un compte?,
                                    <Link to="/register">s'inscrire</Link>
                                </h6>
                            </small>
                        <button type="submit" className="btn btn-dark w-100" to="" >
                            Connexion
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Login;