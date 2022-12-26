import { Button } from '@material-ui/core';
import React from 'react';
import './style.css';

const Opportunity = ({name, content}) => {
  const liked = false;
  return (
    <div className='opportunity-wrapper'>
      <div className='opportunity-header'>
        {name}
      </div>
      <div className='opportunity-body'>
        {content}
      </div>
      <div className='opportunity-buttons'>
        {liked ?
       <Button>Like</Button>
      : <Button>Dislike</Button>}
      </div>
      
    </div>
  )
}

export { Opportunity };