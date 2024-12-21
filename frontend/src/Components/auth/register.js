import React, { useState } from "react";
import './register.css';
import axios from "axios";

function Register() {
    const [formData, setFormData] = useState({nom: "",prenom: "",sexe: "",telephone: "",birthDate: "",poids: "",email: "",password: "",confirmPassword: ""});
    const [errorMessage, setErrorMessage] = useState("")

    const handleDateChange = (e) => {
        setFormData({ ...formData, birthDate: e.target.value });
    };

    const handleChange =(e)=>{
        const {id,value} = e.target;
        setFormData({...formData, [id]:value});
    }

    const handleSubmit = async(e)=>{
        e.preventDefault();
        if(formData.password !== formData.confirmPassword){
            setErrorMessage("les deux mots de passe ne sont pas identiques")
        }
        try{
            await axios.post("http://localhost:8080/api/register", formData);
            console.log("l'utilisateur est bien authentifié");
            setFormData({ nom: "",prenom: "",sexe: "",telephone: "",birthDate: "",poids: "",email: "",password: "",confirmPassword: ""});
        }catch(error){
            console.error("smtg maaachi hia hadek", error);
        }
    }

    return (
        <div className="login-container d-flex align-items-center justify-content-center">
            <div className="login-card2 d-flex">
                <div className="login-form-section">
                    <form className="login-form" onSubmit={handleSubmit}>
                        <h3 className="text-center text-light mb-4">Inscription</h3>

                        <div className="input-group mb-3">
                            <input type="text" className="form-control me-2" placeholder="Nom" aria-label="Nom" id="nom" value={formData.nom} onChange={handleChange} />
                            <input type="text" className="form-control" placeholder="Prenom" aria-label="Prenom" id="prenom" value={formData.prenom} onChange={handleChange}/>
                        </div>

                        <div className="input-group mb-3">
                            <input type="text" className="form-control me-2" placeholder="Sexe" aria-label="Sexe" id="sexe" value={formData.sexe} onChange={handleChange} />
                            <input type="text" className="form-control" placeholder="Téléphone" aria-label="Téléphone" id="telephone" value={formData.telephone} onChange={handleChange} />
                        </div>

                        <div className="input-group mb-3">
                            <input type="date" className="form-control me-2" id="birthDate" value={formData.birthDate}
                                onChange={handleDateChange} min="1940-01-01" max="2018-12-31" aria-label="Date de naissance"/>
                            <input type="text" className="form-control" placeholder="Poids en kg" aria-label="Poids"  onChange={handleChange}/>
                        </div>

                        <div className="form-group mb-3">
                            <input type="email" className="form-control" placeholder="Entrer email" id="email" value={formData.email} onChange={handleChange}/>
                        </div>
                        <div className="form-group mb-3">
                            <input type="password" className="form-control" placeholder="Mot de passe" id="password" value={formData.password} onChange={handleChange}/>
                        </div>
                        <div className="form-group mb-3">
                            <input type="password" className="form-control " placeholder="Confirmer le mot de passe" id="confirmPassword" value={formData.confirmPassword} onChange={handleChange} />
                            <small className="text-light">*{errorMessage}</small>
                        </div>

                        <button type="submit" className="btn btn-success w-100">
                            S'inscrire
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Register;
