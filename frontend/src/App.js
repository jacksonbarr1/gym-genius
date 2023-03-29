import './App.css';
import Register from "./components/Register";
import Login from "./components/Login";
import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Navbar from "./components/Navbar";
import { useSelector } from "react-redux";
import HomePage from "./components/HomePage";
import LogOut from "./components/LogOut";

function App() {

  const state = useSelector(state => state)

  return (

    <Router>
        <Navbar isAuthenticated={state.userAuthentication.isAuthenticated}></Navbar>
      <Routes>
          <Route exact path="/" element={<HomePage />} />
        <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/profile" element={<Register />} />
          <Route path="/logout" element={<LogOut />} />
      </Routes>
    </Router>
  );
}

export default App;
