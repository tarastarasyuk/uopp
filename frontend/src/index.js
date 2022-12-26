import React from 'react';
import ReactDOM from 'react-dom/client';
import App from 'components/app/App';
import { store } from './store';
import { Provider } from 'react-redux';
import { AppContextProvider } from 'providers/appContextProvider';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <AppContextProvider>
      <Provider store={store}>
        <App />
      </Provider>
    </AppContextProvider>
  </React.StrictMode>
);

