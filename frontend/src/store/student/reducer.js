import { DataStatus } from 'common/enums';
import { createReducer } from '@reduxjs/toolkit';
import { createStudent, getStudent } from './actions';
import { notifyError } from 'components/common/forms/sign-up-form/SignUpForm';
import { notify } from 'components/common/forms/sign-in-form/SignInForm';

const initialState = {
    student: null,
    status: DataStatus.IDLE,
};

const reducer = createReducer(initialState, (buider) => {

    buider.addCase(createStudent.pending, (state) => {
        state.status = DataStatus.PENDING
    });

    buider.addCase(createStudent.fulfilled, (state, { payload }) => {
        notify();
        const { student } = payload;
        state.student = student;
        state.status = DataStatus.SUCCESS;
    });

    buider.addCase(createStudent.rejected, (state, {payload}) => {
        notifyError();
        state.status = DataStatus.ERROR;
    });

    buider.addCase(getStudent.pending, (state) => {
        state.status = DataStatus.PENDING
    });

    buider.addCase(getStudent.fulfilled, (state) => {
        state.status = DataStatus.SUCCESS;
    });

});

export { reducer };