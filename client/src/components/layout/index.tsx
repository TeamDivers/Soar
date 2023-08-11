import React from 'react';
import { Outlet } from 'react-router-dom';

import BottomNavigation from '@components/BottomNavigation';

import { Logo } from '../../assets/images';

interface LayoutProps {
    hasNavigation?: boolean;
}

const Layout = ({ hasNavigation = true }: LayoutProps) => {
    // if user is unauthorized, redirect to login

    return (
        <main className="container relative h-full max-w-md mx-auto bg-white">
            <div className="px-5 pt-3">
                <div className="flex">
                    <Logo className="w-16" />
                </div>
                <Outlet />
            </div>
            {hasNavigation && <BottomNavigation />}
        </main>
    );
};

export default Layout;
