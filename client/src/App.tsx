import React from 'react';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';

import { Error, Home, Log, Login, Portfolio, Profile } from '@routes/index';

import Layout from '@components/layout';

function App() {
    const router = createBrowserRouter([
        {
            path: '/',
            element: <Layout />,
            errorElement: <Error />,
            children: [
                { index: true, element: <Home /> },
                { path: '/portfolio', element: <Portfolio /> },
                { path: '/log', element: <Log /> },
                { path: '/profile', element: <Profile /> }
            ]
        },
        {
            path: '/login',
            element: <Login />
        }
    ]);

    return <RouterProvider router={router} />;
}

export default App;
