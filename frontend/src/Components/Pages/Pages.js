import React from "react";
import {Routes, Route} from "react-router-dom";
import Home from "../tools/Home";

import Login from "../auth/login";
import Reset from "../auth/reset";
import Check from "../auth/check";
import Register from "../auth/register";

// Dashboard Directeur
import Dashboard from "../Dashboard-directeur/Dashboard";
import Medecin from "../Dashboard-directeur/Nav-Items/Medecin";
import Patient from "../Dashboard-directeur/Nav-Items/Patient";
import Pharmacien from "../Dashboard-directeur/Nav-Items/Pharmacien";
import Secretaire from "../Dashboard-directeur/Nav-Items/Secretaire";
import ProfileDirecteur from "../Dashboard-directeur/Components/Profile";

function Pages(){
    return(
        <div>
            <Routes>
                <Route path='/' element={<Home/>}></Route>
                <Route path='/login' element={<Login />}></Route>
                <Route path='/reset-password' element={<Reset />}></Route>
                <Route path='/check' element={<Check />}></Route>
                <Route path='/register' element={<Register />}></Route>

                {/* dashboard directeur */}
                <Route path='/dashboard-directeur' element={<Dashboard/>}></Route>
                <Route path="/dashboard-directeur/Medecin" element={<Medecin/>}/>
                <Route path='/dashboard-directeur/Patient' element={<Patient/>}/>
                <Route path='/dashboard-directeur/Pharmacien' element={<Pharmacien/>}/>
                <Route path='/dashboard-directeur/Secretaire' element={<Secretaire/>}/>
                <Route path='/dashboard-directeur/Profile' element={<ProfileDirecteur/>}/>
            </Routes>
        </div>
    )
}
export default Pages;