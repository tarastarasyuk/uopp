import { fetchOpportunities } from 'store/opportunities/actions';
import React, { useEffect } from 'react';
import './style.css';
import { useDispatch, useSelector } from 'react-redux'
import { Layout } from 'components/layout/Layout';
import { DataStatus } from 'common/enums';
import { Opportunity } from 'components/opportunity/Opportunity';

const Home = () => {

  const dispatch = useDispatch();
  const { opportunities, status } = useSelector((state) => state.opportunities);

  useEffect(() => {
    dispatch(fetchOpportunities());
  }, [dispatch]);
  
  useEffect(() => {
    console.log(opportunities);
  }, [opportunities]);
  
  return (
    <div>
        <Layout />
        <div className='opportunities-wrapper'>
          { status === DataStatus.SUCCESS && 
          opportunities.map(opportunity => <Opportunity key={opportunity.id} name={opportunity.name} content={opportunity.content}></Opportunity>)}
        </div>
   </div>
  )
}

export { Home };