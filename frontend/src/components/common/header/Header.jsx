import React from 'react';
import './style.css';
import { makeStyles } from '@material-ui/core';
import { useLocation, useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import { AuthContext } from 'context/auth';
import { OutlinedButton } from 'components/styled/button/outlined/OutlinedButton';
import { ContainedButton } from 'components/styled/button/contained/ContainedButton';
import { ColoredLink } from 'components/styled/link/Link';
import { useSelector } from 'react-redux';

const useStyles = () => makeStyles((theme) => ({
  root: {
      '& > *': {
          margin: theme.spacing(1),
          width: '25ch',
      },
  },
  margin: {
    margin: theme.spacing(1),
  },
}));

const Header = () => {
  const classes = useStyles();
  const navigate = useNavigate();
  const { student } = useSelector((state) => state.profile);
  const { auth } = useContext(AuthContext);
  
  const location = useLocation();
  const path = location.pathname;

  return(
    <header className='header'>
      <nav className='navigation'>
        <ColoredLink onClick={(e) => navigate('/')} style={{color: path === '/' ? '#594BFF' : 'black'}} >Home</ColoredLink>
        {auth && 
        <>
          <ColoredLink onClick={(e) => navigate('/creator')} style={{color: path === '/creator' ? '#594BFF' : 'black'}} >Create</ColoredLink>
          <ColoredLink onClick={(e) => navigate('/editor')} style={{color: path === '/editor' ? '#594BFF' : 'black'}} >Edit</ColoredLink>
        </>}
      </nav>

      {!auth ?
      <div>
        <OutlinedButton style={{marginRight: '10px'}} variant='outlined' color='primary' className={classes.margin} onClick={(e) => navigate('/sign-in')}>Sign in</OutlinedButton>
        <ContainedButton variant='contained' color='primary' className={classes.margin} onClick={(e) => navigate('/sign-up')}>Sign up</ContainedButton>
      </div>
      : <>{student
        ? <div className='avatar' onClick={(e) => navigate('/profile')}>{student.firstName[0].toUpperCase()}</div>
        : <div className='avatar' style={{background: '#594BFF'}} onClick={(e) => navigate('/profile')} />
      }</>}
    </header>
  );
}

export { Header };