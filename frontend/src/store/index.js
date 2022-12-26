import { combineReducers, createStore, configureStore, applyMiddleware } from '@reduxjs/toolkit';
import { composeWithDevTools } from 'redux-devtools-extension';
import { thunk as thunkMiddleware } from './middlewares';
import { opportunities, opportunitiesEditor } from './rootReducer';
import { opportunities as opportunitiesService } from '../services';
import { opportunitiesEditor as opportunitiesEditorService } from '../services';

const store = createStore(
    combineReducers({
        opportunities,
        opportunitiesEditor,
    }),
    composeWithDevTools(
        applyMiddleware(
            thunkMiddleware.withExtraArgument({
                opportunitiesService,
                opportunitiesEditorService,
            })
        )
    )
);

// const store = configureStore({
//     reducer: combineReducers({
//         opportunities,
//         opportunitiesEditor,
//     }),
//     middleware: (getDefaultMiddleware) => 
//     getDefaultMiddleware().concat(thunkMiddleware.withExtraArgument({
//                 opportunitiesService,
//                 opportunitiesEditorService,
//             }))
    
// });

export { store };