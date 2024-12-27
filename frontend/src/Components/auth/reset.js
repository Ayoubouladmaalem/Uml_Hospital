import React, { useState } from "react";
import './reset.css';
import axios from "axios";

function Reset() {
    const [newPassword,setNewPassword]=useState("");
    const [confirmPassword, setConfirmPassword]=useState("");
    const [error, setError] =useState("");
    const [success, setSuccess] =useState("");

    const handleSubmit = async (e) =>{
        e.preventDefault();
        if(newPassword !== confirmPassword){
            setError("Les mots de passe ne correspondent pas")
        }
        try{
            await axios.post("http://localhost:8080/api/reset",{ password : newPassword,});
            setSuccess("votre mot de passe est bien réinitisalisé");
            setNewPassword("");
            setConfirmPassword("");
        }catch(error){
            setError(error.response?.data?.message || "Échec de la réinitialisation du mot de passe.");        }
        }
    return (
        <div className="login-container d-flex align-items-center justify-content-center">
            <div className="login-card3 d-flex">
                <div className="login-form-section">
                    <form className="login-form" onSubmit={handleSubmit}>
                        <h3 className="text-center text-dark mb-5">Réinitialisez votre mot de passe</h3>
                        <div className="form-group mb-3">
                            <input type="password" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Entrer le nouveau mot de passe" />
                        </div>
                        <div className="form-group mb-3">
                            <input type="password" className="form-control" id="exampleInputPassword1" placeholder="Confirmer le nouveau mot de passe"/>
                        </div>
                        <div><small className="text-danger">{error}</small></div>
                        <div><small className="alert text-danger" role="alert">{success}</small></div>
                        <button type="submit" className="btn btn-dark w-100">
                            Submit
                        </button>
                    </form>
                    
                </div>
            </div>
        </div>
    );
}

export default Reset;