import './App.css';
import Register from "./component/Register";
import Login from "./component/Login";
import React from 'react';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';

function App() {

  const padding = {
    padding: 5
  }

  return (
    <Router>
      <Link style={padding} to="/">Home</Link>
      <Link style={padding} to="/register">Register</Link>
        <Link style={padding} to="/login">Login</Link>

      <Routes>
        <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
      </Routes>
    </Router>
  );
}

export default App;
