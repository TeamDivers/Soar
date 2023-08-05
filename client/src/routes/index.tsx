import React from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import Layout from '@components/layout';

import Error from './error';
import Home from './home';
import Log from './log';
import Login from './login';
import Portfolio from './portfolio';
import Profile from './profile';
import Ranking from './ranking';
import Search from './search';

const Router = () => {
    const router = createBrowserRouter([
        {
            path: '/',
            element: <Layout />,
            errorElement: <Error />,
            children: [
                { index: true, element: <Home /> },
                { path: '/portfolio', element: <Portfolio /> },
                { path: '/log', element: <Log /> },
                { path: '/profile', element: <Profile /> },
                { path: '/ranking', element: <Ranking /> },
                { path: '/search', element: <Search /> }
            ]
        },
        {
            path: '/login',
            element: <Login />
        }
    ]);

    return <RouterProvider router={router} />;
};

export default Router;
