import { DataStatus } from 'common/enums';
import { createReducer } from '@reduxjs/toolkit';
import { getProfile, likeOpportunity, unlikeOpportunity } from './actions';

const initialState = {
    student: null,
    status: DataStatus.IDLE,
};

const reducer = createReducer(initialState, (buider) => {

    buider.addCase(getProfile.pending, (state) => {
        state.status = DataStatus.PENDING
    });

    buider.addCase(getProfile.fulfilled, (state, { payload }) => {
        state.student = payload.student;
        state.status = DataStatus.SUCCESS;
    });

    buider.addCase(likeOpportunity.pending, (state) => {
        state.status = DataStatus.PENDING
    });

    buider.addCase(likeOpportunity.fulfilled, (state, { payload }) => {
        const { student } = payload;
        state.student = student;
        state.status = DataStatus.SUCCESS;
    });

    buider.addCase(unlikeOpportunity.pending, (state) => {
        state.status = DataStatus.PENDING
    });

    buider.addCase(unlikeOpportunity.fulfilled, (state, { payload }) => {
        const { student } = payload;
        state.student = student;
        state.status = DataStatus.SUCCESS;
    });

});

export { reducer };