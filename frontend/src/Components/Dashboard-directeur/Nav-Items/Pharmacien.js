import React from 'react';
import Sidebar from '../Sidebar/Sidebar';
import Header from '../Header/Header';
import PharmacienCRUD from '../Components/PharmacienCRUD';

function Pharmacien(){
  return (
    <div style={{ display: 'flex', height: '100vh' }}>
      {/* Sidebar */}
      <Sidebar />
      <div style={{ flex: 1, display: 'flex', flexDirection: 'column' }}>
        {/* Navbar */}
        <Header />
        {/* Dashboard Content */}
        <div style={{ flex: 1, padding: '20px' }}>
          <div style={{ marginTop: '20px' }}>
            <PharmacienCRUD/>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Pharmacien;
