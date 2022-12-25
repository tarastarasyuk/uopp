import { DataStatus } from '../../common/enums';
import { createReducer } from '@reduxjs/toolkit';
import { fetchOpportunities, updateOpportunities } from './actions';

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

    buider.addCase(updateOpportunities.pending, (state) => {
        state.status = DataStatus.PENDING;
    })

    buider.addCase(updateOpportunities.fulfilled, (state, { meta }) => {
        const { arg } = meta;

        // state.subjects = state.subjects.map((item) => {
        //     return item._id === arg._id ? {...item, ...arg} : item;
        // });

        state.status = DataStatus.SUCCESS;
    })

});

export { reducer };