import { ActionType } from './common';
import { createAsyncThunk } from '@reduxjs/toolkit';

const fetchOpportunities = createAsyncThunk(ActionType.GET, async (params, { extra }) => ({
    opportunities: await extra.opportunitiesService.getAll({
        ...params,
    }),
}));

export { fetchOpportunities };