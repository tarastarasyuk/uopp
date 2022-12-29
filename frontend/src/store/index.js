import { combineReducers, configureStore, getDefaultMiddleware } from '@reduxjs/toolkit';
import { opportunities, opportunitiesEditor, student, profile } from './rootReducer';
import { opportunities as opportunitiesService } from '../services';
import { opportunitiesEditor as opportunitiesEditorService } from '../services';
import { student as studentService } from '../services';
import { profile as profileService } from '../services';

const  middleware = getDefaultMiddleware({
  thunk: {
    extraArgument: {
        opportunitiesService,
        opportunitiesEditorService, 
        studentService,
        profileService,
    }
  }
});

const store = configureStore({
    reducer: combineReducers({
        opportunities,
        opportunitiesEditor,
        student,
        profile,
    }),
    middleware,
    
});

export { store };