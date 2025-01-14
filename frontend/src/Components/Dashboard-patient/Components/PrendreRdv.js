import React, { useState } from 'react';
import axios from 'axios';
import './PrendreRdv.css'; // Importez votre fichier CSS personnalisé

function PrendreRdv() {
    const [formData, setFormData] = useState({
        motif: '',
        typeConsultation: '',
        dateConsultation: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const token = localStorage.getItem('token');
            const config = {
                headers: {
                    Authorization: `Bearer ${token}`, // Replace with actual token
                },
            }
            const response = await axios.post('http://localhost:8080/patient/creer-consultation', formData, config);
            alert('Rendez-vous créé avec succès!');
        } catch (error) {
            alert("Erreur lors de la création du rendez-vous. Veuillez réessayer.");
            console.error( error.response || error.message)
        }
    };

    return (
        <div className="container mt-5">
            <form className="form-container mx-auto" onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">Motif :</label>
                    <input
                        type="text"
                        name="motif"
                        className="form-control"
                        value={formData.motif}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Type de Consultation :</label>
                    <select
                        name="typeConsultation"
                        className="form-select"
                        value={formData.typeConsultation}
                        onChange={handleChange}
                        required
                    >
                        <option value="">-- Sélectionnez --</option>
                        <option value="Générale">Générale</option>
                        <option value="Traitement des calculs rénaux par Laser">Traitement des calculs rénaux par Laser</option>
                        <option value="Gynécologie">Gynécologie</option>
                        <option value="Cardiologie">Cardiologie</option>
                        <option value="Neurochirurgie">Neurochirurgie</option>
                        <option value="L'ORL">L'ORL</option>
                        <option value="Chirurgie viscérale">Chirurgie viscérale</option>
                        
                    </select>
                </div>
                <div className="mb-3">
                    <label className="form-label">Date :</label>
                    <input
                        type="date"
                        name="dateConsultation"
                        className="form-control"
                        value={formData.dateConsultation}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary w-100">Envoyer</button>
            </form>
        </div>
    );
}

export default PrendreRdv;
