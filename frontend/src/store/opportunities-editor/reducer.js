import { DataStatus } from '../../common/enums';
import { createReducer } from '@reduxjs/toolkit';
import { createOpportunity, editOpportunity, deleteOpportunity } from './actions';

const initialState = {
    opportunities: [],
    status: DataStatus.IDLE,
};

const reducer = createReducer(initialState, (buider) => {

    buider.addCase(createOpportunity.pending, (state) => {
        state.status = DataStatus.PENDING;
    });

    buider.addCase(createOpportunity.fulfilled, (state, { meta }) => {
        state.status = DataStatus.SUCCESS;
    });

    buider.addCase(editOpportunity.pending, (state) => {
        state.status = DataStatus.PENDING;
    });

    buider.addCase(editOpportunity.fulfilled, (state, { meta }) => {
        const { arg } = meta;

        // state.opportunities = state.opportunities.map((item) => {
        //     return item.id === arg.id ? {...item, ...arg} : item;
        // });

        state.status = DataStatus.SUCCESS;
    });

    buider.addCase(deleteOpportunity.pending, (state) => {
        state.status = DataStatus.PENDING;
    });

    buider.addCase(deleteOpportunity.fulfilled, (state) => {
        state.status = DataStatus.SUCCESS;
    });

});

export { reducer };