import React from 'react';
import './style.css';
import { Button } from '@material-ui/core';
import { useNavigate } from 'react-router-dom';

const Header = ({ auth }) => {
  const navigate = useNavigate();
  return(
    <header className='header'>
      <nav className='navigation'>
        <span>Home</span>
        <span>Opportunities</span>
      </nav>

      {!auth && 
      <div>
        <Button variant='outlined' onClick={(e) => navigate('/sign-in')}>Sign in</Button>
        <Button variant='contained' onClick={(e) => navigate('/sign-up')}>Sign up</Button>
      </div>}
    </header>
  );
}

export { Header };