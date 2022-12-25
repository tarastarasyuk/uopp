import { Button } from '@material-ui/core';
import React from 'react';

const Opportunity = ({name, content, liked}) => {
  return (
    <div className='opportunity-wrapper'>
      <div className='opportunity-header'>
        {name}
      </div>
      <div className='opportunity-body'>
        {content}
      </div>
      {liked ?
       <Button>Like</Button>
      : <Button>Dislike</Button>}
    </div>
  )
}

export { Opportunity };