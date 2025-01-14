import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBell } from '@fortawesome/free-solid-svg-icons';
import './Header.css';
import { Link } from 'react-router-dom';

const Header = () => {
    
    return (
        <div className="container-fluid bg-light">
            <div className="row py-3 d-flex align-items-center mb-3 shadow">
                <div className="col-6 d-flex">
                    <div className="input-group rounded-pill">
                        <input
                            type="text"
                            className="form-control rounded-pill"
                            placeholder="Search"
                        />
                        <span className="input-group-text border-0 bg-transparent rounded-pill"></span>
                    </div>
                </div>
                <div className="col-6 d-flex justify-content-end align-items-center gap-3">
                    <Link to="/dashboard-directeur/Profile" className="btn rounded-circle overflow-hidden" >
                            <img
                                src="https://img.freepik.com/free-vector/isolated-young-handsome-man-different-poses-white-background-illustration_632498-859.jpg?w=740&t=st=1694269159~exp=1694269759~hmac=c8c65095b670091331a550b674305943e6a42c710a45b7356ecfe851d77b916b"
                                alt="Profile"
                                className="img-fluid profile"
                            />
                            
                    </Link>
                    <div className="ml-2 bell-icon">
                        <FontAwesomeIcon icon={faBell} />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Header;
