import { ActionType } from './common';
import { createAsyncThunk } from '@reduxjs/toolkit';

const fetchTetlegramOpportunities = createAsyncThunk(ActionType.GET, async (params, { extra }) => ({
    opportunities: await extra.opportunitiesService.getAll({
        ...params,
    }),
}));

export { fetchTetlegramOpportunities };