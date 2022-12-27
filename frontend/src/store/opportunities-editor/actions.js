import { ActionType } from './common';
import { createAsyncThunk } from '@reduxjs/toolkit';

const fetchOpportunities = createAsyncThunk(ActionType.GET, async (_args, { extra }) => ({
    opportunities: await extra.opportunitiesService.getAll(),
}));

const createOpportunity = createAsyncThunk(ActionType.CREATE, async (params, { extra }) => ({
    opportunity: await extra.opportunitiesEditorService.create(ActionType.CREATE, {
        ...params,
    }),
}));

const editOpportunity = createAsyncThunk(ActionType.EDIT, async (params, { extra }) => ({
    opportunity: await extra.opportunitiesEditorService.edit(ActionType.EDIT, params.id, {
        ...params,
    }),
}));

const deleteOpportunity = createAsyncThunk(ActionType.DELETE, async (params, { extra }) => ({
    opportunity: await extra.opportunitiesEditorService.delete(ActionType.DELETE, params.id),
}));


export { fetchOpportunities, createOpportunity, editOpportunity, deleteOpportunity }