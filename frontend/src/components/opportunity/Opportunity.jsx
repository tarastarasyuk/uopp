import { makeStyles } from '@material-ui/core';
import React, { useContext, useState } from 'react';
import './style.css';
import { AuthContext } from 'context/auth';
import { useSelector } from 'react-redux';
import { useEffect } from 'react';
import { StarRounded } from '@material-ui/icons';
import { ContainedButton } from 'components';

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

const Opportunity = ({id, name, content, like, unlike, asap}) => {
  const classes = useStyles();
  const [liked, setLiked] = useState(false);
  const { auth } = useContext(AuthContext);
  const { student } = useSelector((state) => state.profile);
  
  const clickLike = (e) => {
    like(e); 
    setLiked(true);
  }

  const clickUnlike = (e) => {
    unlike(e);
    setLiked(false);
  }

  useEffect(() => {
    
    if(student){
      if(student.likedOpportunities.find(opportunity => opportunity.id === id)) setLiked(true);
      else setLiked(false);
    }
    console.log(liked);
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [student]);

  return (
    <div className='opportunity-wrapper'>
      {asap && <span className='asap'>ASAP</span>}
      {auth && <>
        {liked && <StarRounded style={{color: '#F5B700', position: 'absolute', right: '10px', top: '10px'}}></StarRounded>}
      </>}
      
      <div className='opportunity-header'>
        {name}
      </div>
      <div className='opportunity-body'>
        {content}
      </div>
      
      <div className='opportunity-buttons' opportunity-id={id}>
        {auth && <>
          {!liked ?
              <ContainedButton variant='contained' color='primary' className={classes.margin} onClick={clickLike}>Like</ContainedButton>
            : <ContainedButton variant='contained' color='primary' className={classes.margin} onClick={clickUnlike}>Unlike</ContainedButton>}
        </>}
        
      </div>
      
    </div>
  )
}

export { Opportunity };