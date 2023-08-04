import React from 'react';
import { QueryClientProvider } from 'react-query';

import { queryClient } from '@configs/reactQuery';

import Router from './routes';

function App() {
    return (
        <QueryClientProvider client={queryClient}>
            <Router />
        </QueryClientProvider>
    );
}

export default App;
