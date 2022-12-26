import React from 'react';
import './style.css';
import { Button, Link } from '@material-ui/core';
import { useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import { AuthContext } from 'context/auth';

const Header = () => {
  const navigate = useNavigate();
  const { auth } = useContext(AuthContext);

  return(
    <header className='header'>
      <nav className='navigation'>
        <Link onClick={(e) => navigate('/')}>Home</Link>
        <Link onClick={(e) => navigate('/')}>Opportunities</Link>
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