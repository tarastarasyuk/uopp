import { ActionType } from './common';
import { createAsyncThunk } from '@reduxjs/toolkit';

const fetchOpportunities = createAsyncThunk(ActionType.GET, async (_args, { extra }) => ({
    opportunities: await extra.opportunitiesService.getAll(),
}));

export { fetchOpportunities }