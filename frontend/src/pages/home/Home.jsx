import { fetchOpportunities } from 'store/opportunities/actions';
import React, { useEffect, useState } from 'react';
import './style.css';
import { useDispatch, useSelector } from 'react-redux'
import { Layout } from 'components/layout/Layout';
import { DataStatus } from 'common/enums';
import { Opportunity } from 'components/opportunity/Opportunity';
import { getProfile, unlikeOpportunity } from 'store/profile/actions';
import { likeOpportunity } from 'store/profile/actions';

const Home = () => {

  const dispatch = useDispatch();
  const { opportunities, status } = useSelector((state) => state.opportunities);

  const [liked, setLiked] = useState(false);
  const [unliked, setUnliked] = useState(false);
  const { student } = useSelector((state) => state.profile);

  useEffect(() => {
    dispatch(fetchOpportunities());
  }, [dispatch]);
  

  const like = (e) => {
    e.preventDefault();

    const params = {
      profileId: student.id,
    }
    
    dispatch(getProfile(params));
    setLiked(true);
  }

  const unlike = (e) => {
    e.preventDefault();

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
        opportunityId: '21',
      }

      dispatch(likeOpportunity(params));
    }

  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [liked]);

  useEffect(() => {
    
    if(unliked) {
      const params = {
        profileId: student.id,
        opportunityId: '21',
      }

      dispatch(unlikeOpportunity(params));
    }

  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [unliked]);

  return (
    <div>
        <Layout />
        <div className='opportunities-wrapper'>
          { status === DataStatus.SUCCESS && 
          opportunities.map(opportunity => 
          <Opportunity key={opportunity.id} name={opportunity.name} content={opportunity.content} like={like} unlike={unlike}></Opportunity>
          )}
        </div>
   </div>
  )
}

export { Home };