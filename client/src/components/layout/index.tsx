import React from 'react';
import { Outlet } from 'react-router-dom';

import BottomNavigation from '@components/BottomNavigation';

const Layout = () => {
    // if user is unauthorized, redirect to login

    return (
        <main className="container relative h-full max-w-md mx-auto bg-white">
            <Outlet />
            <BottomNavigation />
        </main>
    );
};

export default Layout;
