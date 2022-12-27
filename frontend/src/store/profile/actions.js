import { ActionType } from './common';
import { createAsyncThunk } from '@reduxjs/toolkit';

const getProfile = createAsyncThunk(ActionType.GET, async (params, { extra }) => ({
    student: await extra.profileService.getProfile(params.profileId),
}));

const editProfile = createAsyncThunk(ActionType.SAVE, async (params, { extra }) => ({
    student: await extra.profileService.editProfile(params.profileId, {
        ...params
    }),
}));

const likeOpportunity = createAsyncThunk(ActionType.LIKE, async (params, { extra }) => ({
    student: await extra.profileService.likeOpportunity(ActionType.LIKE, params.profileId, params.opportunityId),
}));

const unlikeOpportunity = createAsyncThunk(ActionType.UNLIKE, async (params, { extra }) => ({
    student: await extra.profileService.unlikeOpportunity(ActionType.UNLIKE, params.profileId, params.opportunityId),
}));

export { getProfile, editProfile, likeOpportunity, unlikeOpportunity };