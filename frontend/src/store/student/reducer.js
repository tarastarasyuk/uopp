import { DataStatus } from 'common/enums';
import { createReducer } from '@reduxjs/toolkit';
import { createStudent } from './actions';

const initialState = {
    student: null,
    status: DataStatus.IDLE,
};

const reducer = createReducer(initialState, (buider) => {

    buider.addCase(createStudent.pending, (state) => {
        state.status = DataStatus.PENDING
    });

    buider.addCase(createStudent.fulfilled, (state, { payload }) => {
        state.status = DataStatus.SUCCESS;
    });

});

export { reducer };