import { combineReducers, configureStore, getDefaultMiddleware } from '@reduxjs/toolkit';
import { opportunities, opportunitiesEditor, student, profile, telegramOpportunities } from './rootReducer';
import { opportunities as opportunitiesService } from '../services';
import { opportunitiesEditor as opportunitiesEditorService } from '../services';
import { student as studentService } from '../services';
import { profile as profileService } from '../services';
import { telegramOpportunities as telegramOpportunitiesService } from '../services';

const  middleware = getDefaultMiddleware({
  thunk: {
    extraArgument: {
        opportunitiesService,
        opportunitiesEditorService, 
        studentService,
        profileService,
        telegramOpportunitiesService,
    }
  }
});

const store = configureStore({
    reducer: combineReducers({
        opportunities,
        opportunitiesEditor,
        student,
        profile,
        telegramOpportunities,
    }),
    middleware,
    
});

export { store };