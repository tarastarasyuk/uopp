import React from 'react';
import './style.css';
import { Button } from '@material-ui/core';

const Header = ({ auth }) => {
  return(
    <header className='header'>
      <nav className='navigation'>
        <span>Home</span>
        <span>Opportunities</span>
      </nav>

      {!auth && 
      <div>
        <Button variant='outlined'>Sign in</Button>
        <Button variant='contained'>Register</Button>
      </div>}
    </header>
  );
}

export { Header };