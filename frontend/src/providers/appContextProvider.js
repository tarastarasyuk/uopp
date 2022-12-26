import { combineComponents } from 'utils/combineComponents';
import { AuthProvider } from './auth';

const providers = [
    AuthProvider,
]

export const AppContextProvider = combineComponents(providers);