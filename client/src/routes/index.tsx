import React from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import Layout from '@components/layout';

import Error from './error';
import Home from './home';
import Login from './login';
import OnBoard from './login/onboard';
import SignUp from './login/signup';
import Portfolio from './portfolio';
import PortfolioDetail from './portfolio/[id]';
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
            errorElement: <Error />,
            children: [
                {
                    index: true,
                    element: (
                        <Layout>
                            <Portfolio />
                        </Layout>
                    )
                },
                {
                    path: ':portfolioId',
                    element: (
                        <Layout hasLogo={false} hasNavigation={false}>
                            <PortfolioDetail />
                        </Layout>
                    )
                }
            ]
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
            element: <Layout hasNavigation={false} hasLogo={false} />,
            errorElement: <Error />,
            children: [
                { index: true, element: <Login /> },
                { path: 'signup', element: <SignUp /> },
                { path: 'onboard', element: <OnBoard /> }
            ]
        }
    ]);

    return <RouterProvider router={router} />;
};

export default Router;
