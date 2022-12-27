import { ActionType } from './common';
import { createAsyncThunk } from '@reduxjs/toolkit';

const createStudent = createAsyncThunk(ActionType.CREATE, async (params, { extra }) => ({
    student: await extra.studentService.create(ActionType.CREATE, {
        ...params,
    }),
}));

const getStudent = createAsyncThunk(ActionType.GET, async (params, { extra }) => ({
    student: await extra.studentService.getStudent(ActionType.GET, {
        ...params,
    }),
}));

export { createStudent, getStudent };