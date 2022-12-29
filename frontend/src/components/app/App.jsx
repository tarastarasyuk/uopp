import React, { useContext } from 'react';
import './style.css';
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import { publicRoutes, privateRoutes } from 'router';
import { AuthContext } from 'context/auth';

const App = () => {

  const { auth } = useContext(AuthContext); 

  return (
    <BrowserRouter>
        { auth
        ? <Routes>
            {privateRoutes.map(route =>
                <Route
                    key={route.path}
                    element={route.element}
                    path={route.path}
                    exact={route.exact}
                />
            )}

            <Route path="*" element={<Navigate to ="/" />}/>
        </Routes>
        
        : <Routes>
            {publicRoutes.map(route =>
                <Route
                    key={route.path}
                    element={route.element}
                    path={route.path}
                    exact={route.exact}
                />
            )}

            <Route path="*" element={<Navigate to ="/sign-in" />}/>
        </Routes>
        } 
    </BrowserRouter>
  );
}

export { App };