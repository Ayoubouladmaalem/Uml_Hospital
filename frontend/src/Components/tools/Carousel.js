import React from "react";
import Carousel from 'react-bootstrap/Carousel';
import slide2 from '../images/slide2.jpg';
import slide1 from '../images/slide1.jpg';
import slide3 from '../images/slide3.jpg';
import { Button } from "react-bootstrap";
import './Carousel.css';

function CostumCarousel(){
    return(
    <Carousel data-bs-theme="dark">
      <Carousel.Item className="carousel">
        <img
          className="d-block w-100 bg-dark " src={slide1}
          alt="First slide"
          style={{ height: '500px', objectFit: 'cover', }}
          
        />
        
        <Carousel.Caption>
        <div >
          <h3 className="phrase-slide">Bienvenue à la Clinique du Nord spécialisés</h3>
          </div>
        <Button href="#contact" className="animated-button">Contactez Nous</Button>
        </Carousel.Caption>
      </Carousel.Item>

      <Carousel.Item>
        <img
          className="d-block w-100 " src={slide2}
          alt="First slide"
          style={{ height: '500px', objectFit: 'cover' }}
        />
        <Carousel.Caption>
        <div>
          <h3 className="phrase-slide">Une équipe Multidisciplinaire</h3>
          </div>
        <Button href="#contact" className="animated-button">Contactez Nous</Button>
        </Carousel.Caption>
      </Carousel.Item>

      <Carousel.Item>
        <img
          className="d-block w-100 " src={slide3}
          alt="First slide"
          style={{ height: '500px', objectFit: 'cover' }}
        />
        <Carousel.Caption>
        <div >
          <h3 className="phrase-slide" >Ensemble vers une meilleure santé</h3>
          </div>
        <Button href="#contact" className="animated-button" >Contactez Nous</Button>
        </Carousel.Caption>
      </Carousel.Item>

    </Carousel>
  );
}

export default CostumCarousel;