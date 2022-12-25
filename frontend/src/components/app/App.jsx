import React from 'react';
import './style.css';
import { Header } from 'components/common/header/Header';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Header auth={false}/>}/>
        <Route path='/home' element={<Header auth={false}/>}/>
      </Routes>
    </BrowserRouter>
  );
}

