import React from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import Layout from '@components/layout';

import Error from './error';
import Home from './home';
import Login from './login';
import Kakao from './login/kakao';
import OnBoard from './login/onboard';
import SignUp from './login/signup';
import Portfolio from './portfolio';
import PortfolioDetail from './portfolio/[id]';
import PortfolioCreate from './portfolio/create';
import Profile from './profile';
import Ranking from './ranking';
import Record from './record';
import RecordCreate from './record/create';
import Search from './search';

const Router = () => {
    const router = createBrowserRouter([
        {
            path: '/',
            element: <Layout hasProfile />,
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
                    element: <PortfolioDetail />
                },
                {
                    path: 'create',
                    element: (
                        <Layout hasHeader={false} hasNavigation={false}>
                            <PortfolioCreate />
                        </Layout>
                    )
                }
            ]
        },
        {
            path: '/profile',
            element: <Layout />,
            errorElement: <Error />,
            children: [
                {
                    index: true,
                    element: <Profile />
                }
            ]
        },
        {
            path: '/record',
            errorElement: <Error />,
            children: [
                {
                    index: true,
                    element: (
                        <Layout>
                            <Record />
                        </Layout>
                    )
                },
                {
                    path: 'create',
                    element: (
                        <Layout hasHeader={false} hasNavigation={false}>
                            <RecordCreate />
                        </Layout>
                    )
                }
            ]
        },
        {
            path: '/ranking',

            errorElement: <Error />,
            children: [
                {
                    index: true,
                    element: <Ranking />
                },
                { path: 'search', element: <Search /> }
            ]
        },
        {
            path: '/login',
            element: <Layout hasNavigation={false} hasHeader={false} />,
            errorElement: <Error />,
            children: [
                { index: true, element: <Login /> },
                { path: 'signup', element: <SignUp /> },
                { path: 'onboard', element: <OnBoard /> },
                { path: 'redirect', element: <Kakao /> }
            ]
        }
    ]);

    return <RouterProvider router={router} />;
};

export default Router;
