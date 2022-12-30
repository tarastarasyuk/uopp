import { DataStatus } from 'common/enums';
import { createReducer } from '@reduxjs/toolkit';
import { fetchOpportunities, fetchOpportunitiesFromTelegram, createOpportunity, editOpportunity, deleteOpportunity } from './actions';

const initialState = {
    opportunities: [],
    opportunity: null,
    status: DataStatus.IDLE,
};

const reducer = createReducer(initialState, (buider) => {

    buider.addCase(fetchOpportunities.pending, (state) => {
        state.status = DataStatus.PENDING
    });

    buider.addCase(fetchOpportunities.fulfilled, (state, { payload }) => {
        const { opportunities } = payload;
        state.opportunities = opportunities;
        state.status = DataStatus.SUCCESS;
    });

    buider.addCase(fetchOpportunitiesFromTelegram.pending, (state) => {
        state.status = DataStatus.PENDING;
    });

    buider.addCase(fetchOpportunitiesFromTelegram.fulfilled, (state, { payload }) => {
        const { opportunities } = payload;
        state.opportunities = [...state.opportunities, ...opportunities];
        state.status = DataStatus.SUCCESS;
    });
    
    buider.addCase(createOpportunity.pending, (state) => {
        state.status = DataStatus.PENDING;
    });

    buider.addCase(createOpportunity.fulfilled, (state, { payload }) => {
        const { opportunity } = payload;
        state.opportunity = opportunity;
        state.status = DataStatus.SUCCESS;
    });

    buider.addCase(editOpportunity.pending, (state) => {
        state.status = DataStatus.PENDING;
    });

    buider.addCase(editOpportunity.fulfilled, (state, { payload }) => {
        const { opportunity } = payload;
        
        state.opportunities = state.opportunities.map((opp) => {
            return opp.id === opportunity.id ? {...opp, ...opportunity} : opp;
        });

        state.status = DataStatus.SUCCESS;
    });

    buider.addCase(deleteOpportunity.pending, (state) => {
        state.status = DataStatus.PENDING;
    });

    buider.addCase(deleteOpportunity.fulfilled, (state, { payload }) => {
        const { opportunity } = payload;
        state.opportunities = state.opportunities.filter(opp => opp.id !== opportunity);
        state.status = DataStatus.SUCCESS;
    });

});

export { reducer };