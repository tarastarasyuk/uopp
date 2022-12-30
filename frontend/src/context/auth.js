import { createContext } from 'react';

export const AuthContext = createContext(!!sessionStorage.getItem('token'));
