import React from 'react';
import { Outlet } from 'react-router-dom';

const Layout = () => {
    // if user is unauthorized, redirect to login

    return (
        <main className="container relative h-full max-w-md mx-auto bg-white">
            <Outlet />
        </main>
    );
};

export default Layout;
