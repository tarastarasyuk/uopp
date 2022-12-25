import { ActionType } from './common';
import { createAsyncThunk } from '@reduxjs/toolkit';

const fetchOpportunities = createAsyncThunk(ActionType.GET, async (_args, { extra }) => ({
    opportunities: await extra.opportunitiesService.getAll(),
}));

const updateOpportunities = createAsyncThunk(ActionType.UPDATE, async (params, {extra}) => ({
    opportunities: await extra.opportunitiesService.particalUpdate(params._id, {
        ...params,
    }),
}))

export { fetchOpportunities, updateOpportunities }