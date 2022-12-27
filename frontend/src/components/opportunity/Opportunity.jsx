import { Button, makeStyles, withStyles } from '@material-ui/core';
import React, { useContext, useState } from 'react';
import './style.css';
import { AuthContext } from 'context/auth';
import { useSelector } from 'react-redux';
import { useEffect } from 'react';
import { StarRounded } from '@material-ui/icons';

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

const ColorButton = withStyles((theme) => ({
  root: {
    color: theme.palette.getContrastText('#594BFF'),
    backgroundColor: '#594BFF',
    '&:hover': {
      backgroundColor: '#3E33C2',
    },
    width: '80px',
  },
}))(Button);

const Opportunity = ({id, name, content, like, unlike}) => {
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

  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [liked]);

  return (
    <div className='opportunity-wrapper'>
      {auth && <>
        {liked && <StarRounded style={{ color: '#F5B700', position: 'absolute', right: '10px', top: '10px' }}></StarRounded>}
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
              <ColorButton variant="contained" color="primary" className={classes.margin} onClick={clickLike}>Like</ColorButton>
            : <ColorButton variant="contained" color="primary" className={classes.margin} onClick={clickUnlike}>Unlike</ColorButton>}
        </>}
        
      </div>
      
    </div>
  )
}

export { Opportunity };