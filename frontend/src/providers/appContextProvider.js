import { combineComponents } from 'utils/combineComponents';
import { AuthProvider } from './auth';
import { StudentProvider } from './student';

const providers = [
    AuthProvider,
    StudentProvider,
]

export const AppContextProvider = combineComponents(providers);