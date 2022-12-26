import { ActionType } from './common';
import { createAsyncThunk } from '@reduxjs/toolkit';

const createStudent = createAsyncThunk(ActionType.CREATE, async (params, { extra }) => ({
    student: await extra.studentService.create({
        ...params,
    }),
}));

export { createStudent };