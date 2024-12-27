import {BrowserRouter as Router } from 'react-router-dom';
import './App.css';
import Pages from './Components/Pages/Pages';

import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

function App() {
  return (
    <Router>
        <Pages/>
    </Router>
  )
}

export default App;
