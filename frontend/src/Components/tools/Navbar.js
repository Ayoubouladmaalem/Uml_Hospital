import React from "react";
import logo from "../images/logo.png";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Link } from "react-router-dom";

function CustomNavbar() {
  return (
    <Navbar expand="lg" className="text-dark bg-light" sticky="top">
      <Container fluid >
      <img src={logo} alt="Logo"/>
        <Navbar.Brand href="/"> </Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <div className="d-flex justify-content-end">
        <Navbar.Collapse id="navbarScroll">
          <Nav className="me-auto my-12 my-lg-3" style={{ maxHeight: "100px" }} navbarScroll >
            <Nav.Link href="#Acceuil" className="me-2" >Acceuil</Nav.Link>
            <Nav.Link href="#services" className="me-2 ">Services Médicaux</Nav.Link>
            <Nav.Link href="#contact" className="me-3 ">Contactez Nous</Nav.Link>
          
          </Nav>
          <Form className="d-flex p-2">
            <Button variant="primary me-2" href="#RDV">prendre un rendez-vous</Button>
            <Link to="/register" className="btn btn-outline-success">S'inscrire</Link>
          </Form>
         
        </Navbar.Collapse>
        </div>
      </Container>
    </Navbar>
  );
}

export default CustomNavbar;
