import React,{ useState, useEffect }  from 'react';
import axios from 'axios';

function PatientConsulting(){

    const [userData,setUserData] = useState([]);
    
    //API GET:
    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await axios.get("http://localhost:8080/api/medecins");
                setUserData(res.data);
            } catch (error) {
                console.error("Erreur lors de la récupération des données:", error);
            }
        };
        fetchData();
    }, []);



    return (
        <div className="shadow mx-1 px-3 bg-light rounded-3 ">
            <div className=' d-flex align-items-center justify-content-between p-3 mt-3 ' >
                <h4 className="mb-0 text-primary">Consultation des Patients</h4>
            </div>

            <div className='mt-2 '>
                <table className="table table-striped ">
                    <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Sexe</th>
                            <th>Poids</th>
                            <th>Téléphone</th>
                            <th>Date de naissance</th>
                            <th>E-mail</th>
                            <th>Mot de passe</th>

                        </tr>
                    </thead>
                    <tbody>
                        {userData.map((user, index) => (
                            <tr key={index}>
                            <td>{user.nom}</td>
                            <td>{user.prenom}</td>
                            <td>{user.sexe}</td>
                            <td>{user.Poids}</td>
                            <td>{user.telephone}</td>
                            <td>{user.dateNaissance}</td>
                            <td>{user.email}</td>
                            <td>{user.motDePasse}</td>
                            </tr>
                            
                        ))}
                            
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default PatientConsulting;