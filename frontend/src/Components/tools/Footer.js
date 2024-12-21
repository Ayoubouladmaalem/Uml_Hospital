import React from "react";
import "./Footer.css";
import logo from "../images/logo.png";

function Footer() {
  return (
    <section id="contact" className="footer">
      <div className="text-dark">
        <div className="row">
          <div className="col">
            <img
              src={logo}
              alt="logo"
              style={{ height:'70px' }}
            />
          </div>
          <div className="col">
            <h5 className="text-primary">Contactez nous</h5>
            <h6 className="font">
              246 Rte de Rabat, Tanger 18262 <br />
              +212 (0) 539 24 14 14, communication@nordhopital.ma
            </h6>
          </div>
        </div>
       <div className="underline mt-4 "></div>
       <div><h6 className="font mt-2 text-center"><i>© 2024 Copyright Nord Specialized Hopital All Rights Reserved.</i></h6></div>
      </div>
    </section>
  );
}

export default Footer;
