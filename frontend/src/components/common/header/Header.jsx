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
        <Link onClick={(e) => navigate('/editor')}>Create</Link>
      </nav>

      {!auth ?
      <div>
        <Button variant='outlined' onClick={(e) => navigate('/sign-in')}>Sign in</Button>
        <Button variant='contained' onClick={(e) => navigate('/sign-up')}>Sign up</Button>
      </div>
      : <div style={{'width': '30px', 'height': '30px', 'borderRadius': '50%', 'background': 'red'}} onClick={(e) => navigate('/profile')}></div>}
    </header>
  );
}

export { Header };