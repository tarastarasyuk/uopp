import React from 'react';
import './style.css';
import { Button } from '@material-ui/core';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import { fetchOpportunities } from 'store/opportunities/actions';
import { createOpportunity, editOpportunity, deleteOpportunity,  } from 'store/opportunities-editor/actions';

const Header = ({ auth }) => {

  const { opportunities, status } = useSelector((state) => state);

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchOpportunities());
  }, [dispatch]);

  useEffect(() => {
    console.log(opportunities);
  }, [opportunities]);

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