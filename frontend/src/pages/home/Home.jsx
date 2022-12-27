import { fetchOpportunities } from 'store/opportunities/actions';
import React, { useEffect, useState } from 'react';
import './style.css';
import { useDispatch, useSelector } from 'react-redux';
import { Layout } from 'components/layout/Layout';
import { DataStatus } from 'common/enums';
import { Opportunity } from 'components/opportunity/Opportunity';
import { getProfile, likeOpportunity, unlikeOpportunity } from 'store/profile/actions';
import { InputAdornment, TextField } from '@material-ui/core';
import { Search } from '@material-ui/icons';

const sortings = [
  {
    value: '',
    label: '',
  },
  {
    value: 'newest',
    label: 'New',
  },
  {
    value: 'asap',
    label: 'ASAP',
  },
  {
    value: 'deadline-soon',
    label: 'Deadline',
  },
];

const Home = () => {

  const dispatch = useDispatch();
  const { opportunities, status } = useSelector((state) => state.opportunities);

  const [liked, setLiked] = useState(false);
  const [unliked, setUnliked] = useState(false);
  const [opportunityId, setOpportunityId] = useState();
  const [sort, setSort] = useState('');
  const { student } = useSelector((state) => state.profile);

  useEffect(() => {
    dispatch(fetchOpportunities({sort}));
  }, [dispatch, sort]);
  

  const like = (e) => {
    e.preventDefault();
    const id = e.target.closest('.opportunity-buttons').getAttribute('opportunity-id');
    setOpportunityId(id);

    const params = {
      profileId: student.id,
    }
    
    dispatch(getProfile(params));
    setLiked(true);
  }

  const unlike = (e) => {
    e.preventDefault();
    const id = e.target.closest('.opportunity-buttons').getAttribute('opportunity-id');
    setOpportunityId(id);

    const params = {
      profileId: student.id,
    }
    
    dispatch(getProfile(params));
    setUnliked(true);
  }

  useEffect(() => {
    
    if(liked) {
      const params = {
        profileId: student.id,
        opportunityId,
      }

      dispatch(likeOpportunity(params));
      setLiked(false);
    }

  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [liked]);

  useEffect(() => {
    
    if(unliked) {
      const params = {
        profileId: student.id,
        opportunityId,
      }

      dispatch(unlikeOpportunity(params));
      setUnliked(false);
    }

  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [unliked]);

  const sorting = (e) => {
    setSort(e.target.value);
  }

  return (
    <div>
        <Layout />

        <TextField required className='input' variant='outlined' /*value={deadline} onChange={(e) => setDeadline(e.target.value)}*/ style={{background: 'white'}} InputProps={{
          startAdornment: (
            <InputAdornment position='end'>
              <Search onClick={(e) => console.log('Searching...')} style={{cursor: 'pointer'}}/>
            </InputAdornment>
          )
        }}/>
        <TextField
            id="outlined-select-currency-native"
            select
            label="Sorting"
            value={sort}
            onChange={sorting}
            SelectProps={{
              native: true,
            }}
            variant="outlined"
          >
          {sortings.map((option) => (
            <option key={option.value} value={option.value}>
              {option.label}
            </option>
          ))}
        </TextField>

        <div className='opportunities-wrapper'>
          {status === DataStatus.SUCCESS && 
          opportunities.map(opportunity => 
          <Opportunity key={opportunity.id} id={opportunity.id} name={opportunity.name} content={opportunity.content} like={like} unlike={unlike}></Opportunity>
          )}
        </div>
   </div>
  )
}

export { Home };