import React, { useContext } from 'react';
import './style.css';
import { makeStyles } from '@material-ui/core';
import { useLocation, useNavigate } from 'react-router-dom';
import { AuthContext } from 'context/auth';
import { OutlinedButton, ContainedButton, StyledLink } from 'components';
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

  console.log(auth);
  return(
    <header className='header'>
      <nav className='navigation'>
        <StyledLink onClick={(e) => navigate('/')} style={{color: path === '/' ? '#3273F6' : 'black'}} >Home</StyledLink>
        {auth && 
        <>
          <StyledLink onClick={(e) => navigate('/creator')} style={{color: path === '/creator' ? '#3273F6' : 'black'}} >Create</StyledLink>
          <StyledLink onClick={(e) => navigate('/editor')} style={{color: path === '/editor' ? '#3273F6' : 'black'}} >Edit</StyledLink>
        </>}
      </nav>

      {!auth ?
      <div>
        <OutlinedButton style={{marginRight: '10px'}} variant='outlined' color='primary' className={classes.margin} onClick={(e) => navigate('/sign-in')}>Sign in</OutlinedButton>
        <ContainedButton variant='contained' color='primary' className={classes.margin} onClick={(e) => navigate('/sign-up')}>Sign up</ContainedButton>
      </div>
      : <>{student
        ? <div className='avatar' onClick={(e) => navigate('/profile')}>{student.firstName[0].toUpperCase()}</div>
        : <div className='avatar' style={{background: '#3273F6'}} onClick={(e) => navigate('/profile')} />
      }</>}
    </header>
  );
}

export { Header };