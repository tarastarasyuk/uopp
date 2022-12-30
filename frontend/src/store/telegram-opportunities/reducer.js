import { DataStatus } from 'common/enums';
import { createReducer } from '@reduxjs/toolkit';
import { fetchTetlegramOpportunities } from './actions';

const initialState = {
    opportunities: [],
    status: DataStatus.IDLE,
};

const reducer = createReducer(initialState, (buider) => {

    buider.addCase(fetchTetlegramOpportunities.pending, (state) => {
        state.status = DataStatus.PENDING
    });

    buider.addCase(fetchTetlegramOpportunities.fulfilled, (state, { payload }) => {
        const { opportunities } = payload;
        state.opportunities = opportunities;
        state.status = DataStatus.SUCCESS;
    });
    
});

export { reducer };