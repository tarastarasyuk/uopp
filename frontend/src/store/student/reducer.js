import { DataStatus } from 'common/enums';
import { createReducer } from '@reduxjs/toolkit';
import { createStudent, getStudent } from './actions';

const initialState = {
    student: null,
    status: DataStatus.IDLE,
};

const reducer = createReducer(initialState, (buider) => {

    buider.addCase(createStudent.pending, (state) => {
        state.status = DataStatus.PENDING
    });

    buider.addCase(createStudent.fulfilled, (state, { payload }) => {
        const { student } = payload;
        state.student = student;
        state.status = DataStatus.SUCCESS;
    });

    buider.addCase(getStudent.pending, (state) => {
        state.status = DataStatus.PENDING
    });

    buider.addCase(getStudent.fulfilled, (state, { payload }) => {
        const { student } = payload;
        state.student = student;
        state.status = DataStatus.SUCCESS;
    });

});

export { reducer };