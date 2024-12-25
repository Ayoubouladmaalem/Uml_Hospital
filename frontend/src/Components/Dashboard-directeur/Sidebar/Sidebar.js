import {React} from "react";
import '../Sidebar/Sidebar.css';
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClipboardUser, faCog, faHospitalUser, faUserDoctor, faUserNurse } from "@fortawesome/free-solid-svg-icons";
import logo from "../../images/logo.png"
function Sidebar(){
    return(
        
        <nav1 className="navbar navbar-expand-lg navbar-light ">
            <ul className="navbar-nav d-flex flex-column">
                
                <li className="nav-item logo">
                    <h5>
                        <img src={logo} alt='logo' />
                    </h5>
                </li>

                <li className="nav-item" >
                    <Link className="nav-link" to="/dashboard-directeur/Medecin">
                    <h5>
                        <FontAwesomeIcon icon={faUserDoctor} className="text-info" /> Médecin
                    </h5>
                    </Link>
                </li>
                
                <li className="nav-item">
                    <Link className="nav-link" to="/dashboard-directeur/Pharmacien">
                    <h5>
                        <FontAwesomeIcon icon={faUserNurse} className="text-info"/> Pharmacien
                    </h5>
                    </Link>
                </li>
                
                <li className="nav-item">
                    <Link className="nav-link" to="/dashboard-directeur/Secretaire">
                    <h5>
                        <FontAwesomeIcon icon={faHospitalUser} className="text-info"/> Secretaire
                    </h5>
                    </Link>
                </li>
                
                <li className="nav-item">
                    <Link className="nav-link" to="/dashboard-directeur/Patient">
                    <h5>
                        <FontAwesomeIcon icon={faClipboardUser} className="text-info" /> Patient
                    </h5>
                    </Link>
                </li> 
                <li className="nav-item underline">
                    <h5>
                        <FontAwesomeIcon icon={faCog} className="text-info mt-3"/> Settings
                    </h5>
                </li>
            </ul>
                
        </nav1>
    )
}

export default Sidebar;