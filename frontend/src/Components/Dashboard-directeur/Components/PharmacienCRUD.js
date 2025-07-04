import React,{ useState, useEffect }  from 'react';
import axios from 'axios';
import { faTrash, faPen} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import bootstrap from 'bootstrap/dist/js/bootstrap.bundle';


function PharmacienCRUD(){

    const [userData,setUserData] = useState([]);

    const [isEditMode, setisEditMode] = useState(false);
    const [currentUser, setCurrentUser]= useState(null);

    const handleEditClick =(user)=>{
            setisEditMode(true);
            setCurrentUser(user);
            const modal = new bootstrap.Modal(document.getElementById("AddModal"));
            modal.show();
        }
    const handleAddClick = () => {
        setisEditMode(false);
        setCurrentUser({ nom: "",prenom: "", sexe: "",dateNaissance: "", telephone: "", email: ""});
    };
    
    
    //API GET:
    //API GET:
    useEffect(() => {
        const fetchData = async () => {
            try {
                const token = localStorage.getItem("token");
                const res = await axios.get("http://localhost:8080/directeur/pharmaciens", {headers: {
                    Authorization: `Bearer ${token}`,
                },});
                setUserData(res.data);
            } catch (error) {
                console.error("Erreur lors de la récupération des données:", error);
            }
        };
        fetchData();
    }, []);

    //API POST:
    //handle Input change
    const handleInputChange = (e) => {
        const { id, value } = e.target;
        setCurrentUser((prevUser) => ({
            ...prevUser,
            [id]: value,
        }));
    };

    const handleDateChange = (e) =>{
        setCurrentUser({ ...currentUser, dateNaissance: e.target.value });
    }

    const handleAdd = async (e) => {
        e.preventDefault();
        
        try {
            const token = localStorage.getItem("token");
            const config = {
                headers: {
                  Authorization: `Bearer ${token}`, 
                },
              };
            console.log("Current User Data:", currentUser);
            const res = await axios.post(
                "http://localhost:8080/directeur/creer-pharmacien", currentUser, config
            );
            setUserData((prevData) => [...prevData, res.data]);
            setCurrentUser(null);
            console.log("Pharmacien ajouté avec succès");
        } catch (error) {
            console.error("Erreur lors de l'ajout d'un médecin:", error.response || error.message);
        }
    };

    //Api put:

    const handleUpdate = async (e) =>{
        e.preventDefault(); // to perform asynchronous API calls without reloading the page
        try{
            const token = localStorage.getItem("token");
            const config = {
                headers: {
                  Authorization: `Bearer ${token}`, 
                },
              };
            const res = await axios.put(`http://localhost:8080/directeur/update-pharmacien/${currentUser.id}`, currentUser, config);
            setUserData((prevData) => prevData.map((user)=>
                (user.id === currentUser.id ? res.data :user //Checks if the current user in the array matches the one being updated.
            ))) //If the user's id matches currentUser.id, replace the old user object with the updated res.data, Otherwise, keep the original user object unchanged.
            console.log("pharmacien updated successfuly");
            setCurrentUser(null);
        }catch(error){
            console.log("Error a bro hh",error.response || error.message);
        }
    }

    //API DELETE 

    const handleDelete = async (userId) => {
        const isConfirmed = window.confirm("Are you sure you want to delete?");
        if (isConfirmed) {
            try {
                // Send a DELETE request to the API
                const token = localStorage.getItem("token");
                const config = {
                    headers: {
                    Authorization: `Bearer ${token}`, 
                    },
                };
                await axios.delete(`http://localhost:8080/directeur/supprimer-pharmacien/${userId}`,config);
                
                // Update the state to remove the deleted pharmacien from the list
                setUserData((prevData) => prevData.filter((user) => user.id !== userId));
                
                console.log("pharmacien deleted successfully");
            } catch (error) {
                console.error("Error deleting pharmacien:", error.response || error.message);
            }
        }
    };


    return (
        <div className="shadow mx-1 px-3 bg-light rounded-3 ">
            <div className=' d-flex align-items-center justify-content-between p-3 mt-3 ' >
                <h4 className="mb-0 text-primary">Gestion des Pharmaciens</h4>

                    <button type="button" className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#AddModal" onClick={handleAddClick}>
                    Ajouter
                    </button>

                    <div className="modal fade" id="AddModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="exampleModalLabel"> {isEditMode? "Modifier un Pharmacien": "Ajouter un Pharmacin"}</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <form>
                            <div className="mb-3">
                                <input type="text" className="form-control" id="nom" value={currentUser?.nom || ""} onChange={handleInputChange}  placeholder="le nom" />
                            </div>
                            <div className="mb-3">
                                <input type="text" className="form-control" id="prenom" value={currentUser?.prenom || ""} onChange={handleInputChange} placeholder="le prénom" />
                            </div>
                            <div className="mb-3">
                                <select className="form-select" id="sexe" value={currentUser?.sexe || ""} onChange={handleInputChange} aria-label="Default select example">
                                <option value="">-- Sélectionnez --</option>
                                    <option value="H">Homme</option>
                                    <option value="F">Femme</option>
                                </select>
                            </div>
                            <div className="mb-3">
                                <input type="text" className="form-control" id="telephone" value={currentUser?.telephone || ""} onChange={handleInputChange} placeholder="le numéro de téléphone" />
                            </div>

                            <div className='mb-3'>
                                <input type="date" className="form-control me-2" id="dateNaissance" value={currentUser?.dateNaissance || ""}
                                    onChange={handleDateChange} min="1940-01-01" max="2018-12-31" aria-label="Date de naissance"/>
                            </div>
                            
                            <div className="mb-3">
                                <input type="text" className="form-control" id="email" value={currentUser?.email || ""} onChange={handleInputChange} placeholder="Email" />
                            </div>
                            </form>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-danger" data-bs-dismiss="modal">Fermer</button>
                            <button type="button" className="btn btn-primary" onClick={isEditMode? handleUpdate: handleAdd}>{isEditMode? "Mettre à jour" : "Enregistrer"}</button>
                        </div>
                        </div>
                    </div>
                    </div>
            </div>

            <div className='mt-2 '>
                <table className="table table-striped ">
                    <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Sexe</th>
                            <th>Date de naissance</th>
                            <th>Téléphone</th>
                            <th>E-mail</th>
                            <th>Editer</th>
                            <th>Supprimer</th>

                        </tr>
                    </thead>
                    <tbody>
                        {userData.map((user, index) => (
                            <tr key={index}>
                            <td>{user.nom}</td>
                            <td>{user.prenom}</td>
                            <td>{user.sexe}</td>
                            <td>{user.dateNaissance}</td>
                            <td>{user.telephone}</td>
                            <td>{user.email}</td>
                            <td>
                                <button
                                className="btn text-warning"
                                aria-label={`Edit ${user.nom}`}
                                onClick={() => handleEditClick(user)}
                                >
                                <FontAwesomeIcon icon={faPen} />
                                </button>
                            </td>                           
                            <td>
                                <button
                                className="btn text-danger"
                                aria-label={`Delete ${user.nom}`}
                                onClick={() => handleDelete(user.id)}
                                >
                                <FontAwesomeIcon icon={faTrash} />
                                </button>
                            </td>
                            </tr>
                            
                        ))}
                            
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default PharmacienCRUD;