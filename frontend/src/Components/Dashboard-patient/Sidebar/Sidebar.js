import {React} from "react";
import './Sidebar.css';
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClipboardUser, faCog, faHospitalUser } from "@fortawesome/free-solid-svg-icons";
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
                
                <li className="nav-item">
                    <Link className="nav-link" to="/dashboard-patient">
                    <h5>
                        <FontAwesomeIcon icon={faClipboardUser} className="text-info"/> Mon dossier
                    </h5>
                    </Link>
                </li>
                
                <li className="nav-item">
                    <Link className="nav-link" to="/dashboard-patient/RDV">
                    <h5>
                        <FontAwesomeIcon icon={faHospitalUser} className="text-info" /> Rendez-vous
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