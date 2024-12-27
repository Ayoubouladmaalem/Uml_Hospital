import React from 'react';
import Sidebar from '../Sidebar/Sidebar';
import Header from '../Header/Header';
import PatientConsulting from '../Components/PatientConsulting';

function Patient(){
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
            <PatientConsulting/>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Patient;
