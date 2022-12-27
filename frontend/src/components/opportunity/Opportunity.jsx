import { Button } from '@material-ui/core';
import React, { useContext, useState } from 'react';
import './style.css';
import { AuthContext } from 'context/auth';

const Opportunity = ({name, content, like, unlike}) => {
  const [liked, setLiked] = useState(false);
  const { auth } = useContext(AuthContext);
  
  const clickLike = (e) => {
    like(e);
    setLiked(!liked);
  }

  const clickUnlike = (e) => {
    unlike(e);
    setLiked(!liked);
  }

  return (
    <div className='opportunity-wrapper'>
      <div className='opportunity-header'>
        {name}
      </div>
      <div className='opportunity-body'>
        {content}
      </div>
      <div className='opportunity-buttons'>
        {auth && <>
            {!liked ?
          <Button onClick={clickLike}>Like</Button>
          : <Button onClick={clickUnlike}>Dislike</Button>}
        </>}
        
      </div>
      
    </div>
  )
}

export { Opportunity };