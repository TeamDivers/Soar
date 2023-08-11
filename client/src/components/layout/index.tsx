import React from 'react';
import { Outlet } from 'react-router-dom';

import BottomNavigation from '@components/BottomNavigation';

import { Logo } from '../../assets/images';

const Layout = () => {
    // if user is unauthorized, redirect to login

    return (
        <main className="container relative h-full max-w-md px-5 pt-3 mx-auto bg-white">
            <Logo className="w-16" />
            <Outlet />
            <BottomNavigation />
        </main>
    );
};

export default Layout;
