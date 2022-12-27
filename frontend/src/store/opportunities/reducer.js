import { DataStatus } from 'common/enums';
import { createReducer } from '@reduxjs/toolkit';
import { fetchOpportunities } from './actions';

const initialState = {
    opportunities: [],
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
    
});

export { reducer };