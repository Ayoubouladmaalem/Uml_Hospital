import React from 'react';
import { useState, useEffect }  from 'react';
import axios from 'axios';
import Sidebar from '../Sidebar/Sidebar';
import Header from '../Header/Header';

function ProfileDirecteur(){

    const [userData, setUserData] = useState(null);
    const [currentUser, setCurrentUser] = useState({});

    //Get API:
    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await axios.get("http://localhost:8080/api/directeur");
                setUserData(res.data);
                setCurrentUser(res.data); // Initialize currentUser with fetched data ashb!!
            } catch (error) {
                console.error("Erreur lors de la récupération des données:", error);
            }
        };
        fetchData();
    }, []);

    //PUT API:

    const handleInputChange =(e)=>{
        const {id, value} = e.target;
        setCurrentUser((prev) =>
        ({...prev,[id]:value,})
        )
    }


    const handleUpdate = async (e) =>{
        e.preventDefault();
        try{
            const res = await axios.put(`http://localhost:8080/api/directeur/${currentUser.id}`,currentUser);
            setUserData(res.data);
            console.log("Profile updated successfully");
            setCurrentUser(res.data);
        }catch(error){
            console.log("ERROR 3awed a ba diale hh", error.response || error.message)
        }
    }


    return (
    <div style={{ display: 'flex', height: '100vh' }}>
      {/* Sidebar */}
      <Sidebar/>
      <div style={{ flex: 1, display: 'flex', flexDirection: 'column' }}>
        
        {/* Navbar */}
        <Header/>
        <div className="shadow mx-1 px-3 bg-light rounded-3 ">
            <div className=' d-flex align-items-center justify-content-between p-3 mt-3 ' >
                <h4 className="mb-0 text-primary">Profile de directeur</h4>
                <button type="button" className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#AddModal">
                    Modifier
                </button>

                <div className="modal fade" id="AddModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="exampleModalLabel"> Modifier Le Profile</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <form>
                            <div className="mb-3">
                                <input type="text" className="form-control" id="nom" value={userData.nom} onChange={handleInputChange}   placeholder="le nom" />
                            </div>
                            <div className="mb-3">
                                <input type="text" className="form-control" id="prenom" value={userData.prenom} onChange={handleInputChange}   placeholder="le prénom" />
                            </div>
                            <div className="mb-3">
                                <input type="text" className="form-control" id="sexe" value={userData.sexe} onChange={handleInputChange}  placeholder="sexe" />
                            </div>
                            <div className="mb-3">
                                <input type="text" className="form-control" id="telephone" value={userData.telephone} onChange={handleInputChange} placeholder="le numéro de téléphone" />
                            </div>
                            <div className="mb-3">
                                <input type="text" className="form-control" id="Datedenaissance"value={userData.Datedenaissance} onChange={handleInputChange}   placeholder="la date de naissance (jj-mm-aaaa)" />
                            </div>
                            <div className="mb-3">
                                <input type="text" className="form-control" id="Email" value={userData.Email} onChange={handleInputChange}  placeholder="Email" />
                            </div>
                            </form>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-danger" data-bs-dismiss="modal">Fermer</button>
                            <button type="button" className="btn btn-primary" onClick={handleUpdate}>Mettre à jour</button>
                        </div>
                        </div>
                    </div>
                    </div>

            </div>

        <div className='container mb-5'>
            <div className='row'>
                <div className='col-md'>
                <ul className="list-group">
                    <li className="list-group-item">Nom: {userData.nom} </li>
                    <li className="list-group-item">Prénom: {userData.prenom}</li>
                    <li className="list-group-item">Sexe: {userData.sexe}</li>
                    <li className="list-group-item">Date de naissance: {userData.Datedenaissance} </li>
                </ul>
                </div>

                <div className='col-md'>
                <ul className="list-group">
                    <li className="list-group-item">Téléphone: {userData.telephone}</li>
                    <li className="list-group-item">E-mail: {userData.email}</li>
                </ul>
                </div>
            </div>
        </div>    
            
        </div>
        </div>
    </div>
    );
};

export default ProfileDirecteur;