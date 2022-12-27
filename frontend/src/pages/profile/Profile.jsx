import { Layout } from 'components/layout/Layout';
import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { DataStatus } from 'common/enums';
import { Opportunity } from 'components/opportunity/Opportunity';
import { getProfile, unlikeOpportunity } from 'store/profile/actions';
import { ProfileEditorForm } from 'components/common/forms/profile-editor-form/ProfileEditorForm';
import './style.css';

const Profile = () => {
  const { student, status } = useSelector((state) => state.profile);
  const dispatch = useDispatch();

  const [unliked, setUnliked] = useState(false);
  const [opportunityId, setOpportunityId] = useState();

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
    
    if(unliked) {
      const params = {
        profileId: student.id,
        opportunityId,
      }

      dispatch(unlikeOpportunity(params));
    }

  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [unliked]);

  return (
    <div>
      <Layout />
      <div className='profile-wrapper'>
        <ProfileEditorForm />
        <div className='liked-opportunities-wrapper'>
          {status === DataStatus.SUCCESS && 
            student.likedOpportunities.map(opportunity => 
            <Opportunity key={opportunity.id} id={opportunity.id} name={opportunity.name} content={opportunity.content} unlike={unlike}></Opportunity>
          )}
        </div>
      </div>
      
    </div>
  )
}

export { Profile };