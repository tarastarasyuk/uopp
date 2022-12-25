import { configureStore } from '@reduxjs/toolkit';
import { opportunities } from './rootReducer';
import { opportunities as opportunitiesService } from '../services';

const store = configureStore({
    reducer: opportunities,
    middleware: (getDefaultMiddleware) => {
        return getDefaultMiddleware({
            thunk: {
                extraArgument: {
                    opportunitiesService,
                }
            }
        })
    }
});

export { store };