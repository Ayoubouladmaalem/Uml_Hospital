import React from "react";  
import Carousel from "./Carousel";
import Navbar from "./Navbar";
import './Home.css';
import hopital from '../images/hopital.jpg';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'; // React component
import { faCity, faLandmark, faBed, faFlask, faUserDoctor, faAddressBook, faPhone, faAddressCard } from '@fortawesome/free-solid-svg-icons';
import Footer from "./Footer";
import service2 from '../images/service2.png';
import service1 from '../images/service1.jpg';
import service3 from '../images/service3.png';
import service4 from '../images/service4.png';
import service5 from '../images/service5.jpg';
import service6 from '../images/service6.jpg';
import {Link} from 'react-router-dom';
 // import FormfacadeEmbed from "@formfacade/embed-react";




function Home(){
    return(
    <div>
    <Navbar />  
    <div className="mb-5"><Carousel/></div>

    {/* Why us? */}
    <section id="Acceuil" className="section1 mt-5">
      <div className="container">
        <div className="row">
          <div className="col-lg-4 me-auto d-flex align-items-stretch">
            <div className="shadow content">
              <h3>Pourquoi nous?</h3>
              <p>
                Notre établissement est une clinique multidisciplinaire qui regroupe différentes spécialités médico-chirurgicales : Chirurgie endoscopique, Réanimation, Gastro-entérologie, Urologie, Traumatologie, Othopédie, Matérnité, Gynécologie, et d’autres spécialités..
              </p>
              <div className="text-center">
                <a href="#services" className="btn btn-info">En savoir plus </a>
              </div>
            </div>
          </div>
          <div className="col-lg-8 d-flex align-items-stretch">
            <div className="icon-boxes d-flex flex-column justify-content-center">
              <div className="row">
                <div className="shadow me-3 card col-xl-4 d-flex align-items-stretch">
                <div className="card-body">
                <div className="mb-4"><i class="bi bi-copy text-primary d-flex justify-content-center"></i></div>
                  <div className="mt-4 mt-xl-0">
                    <h4>Conseils selon le profil </h4>
                    <p>
                      Les interventions sont adaptées en fonction du profil de chaque patient.</p>
                  </div>
                </div>
                </div>
                <div className="shadow card col-xl-4 d-flex align-items-stretch">
                <div className="card-body  ">
                <div className="mb-4"><i class="bi bi-telephone-fill text-primary d-flex justify-content-center "></i></div>
                  <div className="mt-4 mt-xl-0">
                    <h4>24/7 Support</h4>
                    <p>
                      Un service d'assistance téléphonique pour vous assister .</p>
                  </div>
                </div>
                </div>
                
              </div>
            </div>
          </div>
        </div>

      </div>
    </section>

    {/* Second section */}

    <section className="section2 ">
      <div className="container mt-5">
        <div className="row">
          <div className="col"> 
              <img src={hopital} 
              alt="hopital"
              style={{ height: '400px', objectFit: 'cover' }}
              />
          </div>
          <div className="col">
            <h3>Fort de l'expérience de son staff médical complet et de son infrastructure de pointe</h3>
            <p>La clinique spécialisés du Nord jouit d'une notoriété particulière dans le paysage médical de Tanger.</p>

            <div class="icon-box">
              <div class="icon"><i class="bi bi-gear-wide-connected text-primary"></i></div>
              <h4 class="title ms-5">Assistance personnalisée </h4>
              <p class="description ms-5">Notre personnel de soutien hautement qualifiée.</p>
            </div>

            <div class="icon-box">
              <div class="icon"><i class="bi bi-gift-fill text-primary"></i></div>
              <h4 class="title ms-5">Prise en charge rapide</h4>
              <p class="description ms-5">Des équipes réactives pour répondre aux besoins urgents. </p>
            </div>

            <div class="icon-box">
              <div class="icon"><i class="bi bi-file-earmark-medical-fill text-primary"></i></div>
              <h4 class="title ms-5">Consultation médical</h4>
              <p class="description ms-5">Des médecins Généraliste expériementés pour vous fournir les meilleurs Conseils.</p>
            </div>
          </div>
          </div>
      </div>
    </section>

    {/* third section */}
    <section className="section3  mt-5 bg-light">
      <div className="container">
        <div className="row row-cols-1 row-cols-lg-3 g-4">
          <div className="icon col">
            <div className="mt-2 d-flex justify-content-center text-primary">
          <FontAwesomeIcon icon={faLandmark} />
          </div>
                <h3 className="card-title text-center text-primary">6</h3>
                <p className="card-text text-center">Etablissements</p>
          </div>
          <div className="icon col">
            <div className="mt-2 d-flex justify-content-center text-primary">
              <FontAwesomeIcon icon={faBed} />
            </div>
                <h3 className="card-title text-center text-primary">549</h3>
                <p className="card-text text-center">Lits</p>
          </div>

          <div className="icon col">
            <div className="mt-2 d-flex justify-content-center text-primary">
            <FontAwesomeIcon icon={faCity} />
          </div>
                <h3 className="card-title text-center text-primary">183</h3>
                <p className="card-text text-center">Chambres</p>
          </div>


          <div className="icon col">
            <div className="mt-2 d-flex justify-content-center text-primary">
            <FontAwesomeIcon icon={faFlask} />
            </div>
                <h3 className="card-title text-center text-primary">50</h3>
                <p className="card-text text-center">Bloc Opératoire</p>
          </div>
          
          <div className="icon col">
            <div className="mt-2 d-flex justify-content-center text-primary">
            <FontAwesomeIcon icon={faUserDoctor} />
          </div>
                <h3 className="card-title text-center text-primary">334</h3>
                <p className="card-text text-center">Médecins</p>
          </div>

          <div className="icon col">
            <div className="mt-2 d-flex justify-content-center text-primary">
            <FontAwesomeIcon icon={faCity} />
          </div>
                <h3 className="card-title text-center text-primary">3</h3>
                <p className="card-text text-center">Villes</p>
          </div>
          

          
        </div>  

      
      
       


      </div>
    </section>

    {/* fourth section */}
    <section id="services" className="section4 mt-5 ">
      <div className="d-flex justify-content-center mb-4">
        <h3 className="text-primary text-decoration-underline fw-bold">
          Nos Services Médicaux
        </h3>
      </div>
      <div className="row row-cols-1 row-cols-md-1 g-4">
        {[service1, service2, service3, service4, service5, service6].map(
          (service, index) => (
            <div className="col" key={index}>
              <div
                className="card h-100 shadow-sm rounded"
                style={{ transition: "transform 0.3s, box-shadow 0.3s" }}
                onMouseEnter={(e) => {
                  e.currentTarget.style.transform = "scale(1.05)";
                  e.currentTarget.style.boxShadow = "0 6px 20px rgba(0, 0, 0, 0.1)";
                }}
                onMouseLeave={(e) => {
                  e.currentTarget.style.transform = "scale(1)";
                  e.currentTarget.style.boxShadow = "0 4px 10px rgba(0, 0, 0, 0.1)";
                }}
              >
                <div className="d-flex justify-content-center mt-3">
                  <img
                    src={service}
                    alt='service'
                    style={{ height: "100px", objectFit: "contain" }}
                  />
                </div>
                <div className="card-body text-center">
                  <h5 className="card-title text-primary fw-bold">
                    {[
                      "Traitement des calculs rénaux par Laser",
                      "Gynécologie",
                      "Cardiologie",
                      "Neurochirurgie",
                      "L'ORL",
                      "Chirurgie viscérale",
                    ][index]}
                  </h5>
                  <p className="card-text">
                    {[
                      "Une approche moderne du traitement des calculs du rein et de l'uretère.",
                      "La gynécologie est une spécialité médico-chirurgicale qui s'occupe de la physiologie et des maladies de l'appareil génital féminin.",
                      "C’est une spécialité chirurgicale traitant les affections du cœur et des gros vaisseaux thoraciques. Les maladies cardiovasculaires représentent la première cause de mortalité au Maroc.",
                      "C’est la spécialité qui voit au dépistage, au diagnostic, au traitement et à la prévention des maladies et des troubles médicaux du système nerveux, tant dans le cerveau que dans la région de la colonne vertébrale et des nerfs périphériques.",
                      "Un médecin ORL, pour oto-rhino-laryngologiste, est spécialisé dans le diagnostic et la prise en charge des affections et des troubles de la « sphère ORL ». Cette dernière regroupe : le visage, l'oreille (interne et externe).",
                      "La chirurgie viscérale désigne une branche de la chirurgie qui s’intéresse au diagnostic et à la prise en charge des pathologies des organes abdomino-pelviens. On parle aussi de chirurgie digestive.",
                    ][index]}
                  </p>
                </div>
              </div>
            </div>
          )
        )}
      </div>
    </section>


    {/* fifth section */}
    <section id='RDV' className="section5 mt-5 bg-light ">
      <div className="row row-cols-1 row-cols-lg-2">
        <div className="row">
        <div className="col ">
            <h2 className="text-primary mb-3 mt-5 text-center " style={{fontFamily:'Arial'}}>Contact</h2>
              <div className="text-center">
                <h4 className="mb-3"><FontAwesomeIcon icon={faAddressBook} className="text-primary "/>  Adresse :</h4>  <p>246 route de Rabat, Tanger 18262</p>
                <h4 className="mb-3"><FontAwesomeIcon icon={faPhone} className="text-primary"/>  Téléphone:</h4>  <p>+212 (0) 539 24 14 14</p>
                <h4 className="mb-3"><FontAwesomeIcon icon={faAddressCard} className="text-primary "/>  Email:</h4>  <p>communication@nordhopital.ma</p>
              </div>
              </div>
        </div>
        <div className="row">
          <div className="col">
          {/* <FormfacadeEmbed

        formFacadeURL="https://formfacade.com/include/115242247700906050869/form/1FAIpQLScew83P4rLworKxiHbmxfglTMBL2ezB0l2WmFFclbXCWyWtYw/classic.js/?div=ff-compose"

        onSubmitForm={() => console.log('Form submitted')}

        /> */}

        <div className="mt-5 d-flex justify-content-center align-items-center">
          <h3 className="me-5 text-primary"> Prise de rendez-vous</h3>
        </div>
        <div className="mt-5 d-flex justify-content-center align-items-center">  
          <h4 className="me-5 ">Veuillez se connecter Pour prendre un rendez-vous  </h4>
        </div>
        <div className="mt-2 d-flex justify-content-center align-items-center">
          <Link to="/login" className="btn btn-outline-success ">Se connecter</Link>
        </div>
          </div>
        </div>

      </div>
    </section>

    
    <Footer/>
    </div>   
    )
}
export default Home;