import {React} from "react";
import '../Sidebar/Sidebar.css';
import { Link } from "react-router-dom";

function Sidebar(){
    return(
        
        <nav1 className="navbar navbar-expand-lg navbar-light ">
            <ul className="navbar-nav d-flex flex-column">
                <li className="nav-item" ><Link className="nav-link" to="/dashboard-directeur/Medecin">Médecin</Link></li>
                <li className="nav-item"><Link className="nav-link" to="/dashboard-directeur/Pharmacien">Pharmacien</Link></li>
                <li className="nav-item"><Link className="nav-link" to="/dashboard-directeur/Secretaire">Secretaire</Link></li>
                <li className="nav-item"><Link className="nav-link" to="/dashboard-directeur/Patient">Patient</Link></li> 
            </ul>
        </nav1>
    )
}

export default Sidebar;