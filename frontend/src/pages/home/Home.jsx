import { fetchOpportunities } from 'store/opportunities-editor/actions';
import React, { useEffect, useState } from 'react';
import './style.css';
import { useDispatch, useSelector } from 'react-redux';
import { Layout, Opportunity, SearchForm } from 'components';
import { DataStatus } from 'common/enums';
import { getProfile, likeOpportunity, unlikeOpportunity } from 'store/profile/actions';

const Home = () => {

  const dispatch = useDispatch();
  const { opportunities, status } = useSelector((state) => state.opportunitiesEditor);

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
    setOpportunityId(e.target.closest('.opportunity-buttons').getAttribute('opportunity-id'));
    setLiked(true);
  }

  const unlike = (e) => {
    e.preventDefault();
    setOpportunityId(e.target.closest('.opportunity-buttons').getAttribute('opportunity-id'));
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

  return (
    <div>
        <Layout />
        <SearchForm sort={sort} setSort={setSort} />

        <div className='opportunities-wrapper'>
          {status === DataStatus.SUCCESS && 
          opportunities.map(opportunity => 
          <Opportunity key={opportunity.id} id={opportunity.id} name={opportunity.name} content={opportunity.content} like={like} unlike={unlike} asap={opportunity.asap}></Opportunity>
          )}
        </div>
   </div>
  )
}

export { Home };