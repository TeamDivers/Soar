import React from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import Layout from '@components/layout';

import Error from './error';
import Home from './home';
import Login from './login';
import Portfolio from './portfolio';
import Profile from './profile';
import Ranking from './ranking';
import Record from './record';
import Search from './search';

const Router = () => {
    const router = createBrowserRouter([
        {
            path: '/',
            element: <Layout />,
            errorElement: <Error />,
            children: [{ index: true, element: <Home /> }]
        },
        {
            path: '/portfolio',
            element: <Layout />,
            errorElement: <Error />,
            children: [{ index: true, element: <Portfolio /> }]
        },
        {
            path: '/profile',
            element: <Layout />,
            errorElement: <Error />,
            children: [{ index: true, element: <Profile /> }]
        },
        {
            path: '/record',
            element: <Layout />,
            errorElement: <Error />,
            children: [{ index: true, element: <Record /> }]
        },
        {
            path: '/ranking',
            element: <Layout />,
            errorElement: <Error />,
            children: [
                { index: true, element: <Ranking /> },
                { path: 'search', element: <Search /> }
            ]
        },
        {
            path: '/login',
            element: (
                <Layout
                    hasNavigation={false}
                    hasLogo={false}
                    hasPadding={false}
                />
            ),
            errorElement: <Error />,
            children: [
                { index: true, element: <Login /> },
                { path: 'search', element: <Search /> }
            ]
        }
    ]);

    return <RouterProvider router={router} />;
};

export default Router;
