import React, { useState } from "react";
import './reset.css';
import { Link } from "react-router-dom";
import axios from "axios";

function Check() {
    const [isOpen, setiIsOpen] =useState(false);
    const [email,setEmail] = useState("");
    const [verificationCode, setVerificationCode] = useState("");
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const handleSendCode = async (e) =>{
        e.preventDefault();
        
        try{
            await axios.post("http://localhost:8080/api/checkmail", {email});
            setSuccess("Code envoyé avec succès ");
            setiIsOpen(true);
        }catch(err){
            setError(err.response?.data || "Une erreur s'est produite");
        }
    }
    
    const handleVerifyCode = async (e) =>{
        e.preventDefault();
        try{
            await axios.post("http://localhost:8080/api/verify-code", {email , code : verificationCode });
            setSuccess("Code vérifié avec succès !");
        }catch(err){
            setError(err.response?.data || "Code invalide")
        }
    }




    return (
        <div className="login-container d-flex align-items-center justify-content-center">
            <div className="login-card d-flex">
                <div className="login-form-section">
                    <form className="login-form">
                        {!isOpen? (
                        <>
                        <h5 className="text-center text-dark mb-2">Entrez votre email</h5>
                        <div className="form-group mb-3">
                            <input type="email" className="form-control" id="email" 
                            aria-describedby="emailHelp" placeholder="Entrer votre email" value={email} onChange={(e)=>setEmail(e.target.value)} />
                        </div>
                        <button type="submit" className="btn btn-dark w-100" onClick={handleSendCode} >
                            Envoyer le code
                        </button>
                        </>
                        ):(
                        <>
                        <div className="mt-2">
                            <h5 className="text-center" > Entrer le code de verification reçu par mail</h5>
                            {error && <div className="alert alert-danger">{error}</div>}
                            {success && <div className="alert alert-success">{success}</div>}
                            <div className="form-group mb-3">
                                <input type="text" className="form-control" id="code" 
                                aria-describedby="Code" placeholder="Code de vérification" value={verificationCode} onChange={(e)=>setVerificationCode(e.target.value)} />
                            </div>
                                <button className="btn btn-dark w-100" onClick={handleVerifyCode}>
                                    Vérifier
                                </button>
                                <Link to="/reset-password" className="btn btn-outline-dark w-100 mt-2">
                                    Continue
                                </Link>
                            </div>
                        </>
                        )}
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Check;