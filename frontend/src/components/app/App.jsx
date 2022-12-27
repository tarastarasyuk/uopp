import React from 'react';
import './style.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Home, SignUp, SignIn, Profile, Creator, Editor } from 'pages';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home />}/>
        <Route path='/sign-up' element={<SignUp />}/>
        <Route path='/sign-in' element={<SignIn />}/>
        <Route path='/creator' element={<Creator />}/>
        <Route path='/editor' element={<Editor />}/>
        <Route path='/profile' element={<Profile />}/>
      </Routes>
    </BrowserRouter>
  );
}

