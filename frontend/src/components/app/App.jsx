import React from 'react';
import './style.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Home, SignUp, SignIn, Profile } from 'pages';
import { useContext } from 'react';
import { AuthContext } from 'context/auth';
import { useEffect } from 'react';

export default function App() {

  const { setAuth } = useContext(AuthContext);

  useEffect(() => {
    setAuth(localStorage.getItem('auth'));
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home />}/>
        <Route path='/sign-up' element={<SignUp />}/>
        <Route path='/sign-in' element={<SignIn />}/>
        <Route path='/profile' element={<Profile />}/>
      </Routes>
    </BrowserRouter>
  );
}

