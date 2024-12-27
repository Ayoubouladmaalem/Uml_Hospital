import React, { useState } from "react";
import './register.css';
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Register() {
    const [formData, setFormData] = useState({nom: "",prenom: "",sexe: "",telephone: "",dateNaissance: "",poids: "",email: "",motDePasse: "",confirmationMotDePasse: ""});
    const [errorMessage, setErrorMessage] = useState("")
    const navigate = useNavigate(); 
    const handleDateChange = (e) => {
        setFormData({ ...formData, dateNaissance: e.target.value });
    };

    const handleChange =(e)=>{
        const {id,value} = e.target;
        setFormData({...formData, [id]:value});
    }

    const formattedData = { ...formData, poids: parseFloat(formData.poids) };
    const handleSubmit = async(e)=>{
        e.preventDefault();
        if(formData.motDePasse !== formData.confirmationMotDePasse){
            setErrorMessage("les deux mots de passe ne sont pas identiques")
        }
        if (!formData.poids || isNaN(formData.poids) || formData.poids <= 0) {
            setErrorMessage("Le poids doit être un nombre supérieur à 0");
            return;
        }
        try{
            await axios.post("http://localhost:8080/auth/register", formattedData);
            console.log("l'utilisateur est bien authentifié");
            navigate("/login");
            setFormData({ nom: "",prenom: "",sexe: "",telephone: "",dateNaissance: "",poids: "",email: "",motDePasse: "",confirmationMotDePasse: ""});
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
                            <input type="date" className="form-control me-2" id="dateNaissance" value={formData.dateNaissance}
                                onChange={handleDateChange} min="1940-01-01" max="2018-12-31" aria-label="Date de naissance"/>
                            <input 
                                type="number" 
                                className="form-control" 
                                placeholder="Poids en kg" 
                                aria-label="Poids" 
                                id="poids" 
                                value={formData.poids} 
                                onChange={handleChange} 
                                required 
                                min="1" 
                            />                        
                        </div>

                        <div className="form-group mb-3">
                            <input type="email" className="form-control" placeholder="Entrer email" id="email" value={formData.email} onChange={handleChange}/>
                        </div>
                        <div className="form-group mb-3">
                            <input type="password" className="form-control" placeholder="Mot de passe" id="motDePasse" value={formData.motDePasse} onChange={handleChange}/>
                        </div>
                        <div className="form-group mb-3">
                            <input type="password" className="form-control " placeholder="Confirmer le mot de passe" id="confirmationMotDePasse" value={formData.confirmationMotDePasse} onChange={handleChange} />
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
