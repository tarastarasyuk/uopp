import { combineReducers, createStore, configureStore, applyMiddleware } from '@reduxjs/toolkit';
import { composeWithDevTools } from 'redux-devtools-extension';
import { thunk as thunkMiddleware } from './middlewares';
import { opportunities, opportunitiesEditor, student } from './rootReducer';
import { opportunities as opportunitiesService } from '../services';
import { opportunitiesEditor as opportunitiesEditorService } from '../services';
import { student as studentService } from '../services';

const store = createStore(
    combineReducers({
        opportunities,
        opportunitiesEditor,
        student,
    }),
    composeWithDevTools(
        applyMiddleware(
            thunkMiddleware.withExtraArgument({
                opportunitiesService,
                opportunitiesEditorService,
                studentService,
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