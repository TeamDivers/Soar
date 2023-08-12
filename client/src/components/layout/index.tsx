import React from 'react';
import { Outlet } from 'react-router-dom';

import BottomNavigation from '@components/BottomNavigation';

import { Logo } from '../../assets/images';

interface LayoutProps {
    hasNavigation?: boolean;
    hasLogo?: boolean;
}

const Layout = ({ hasNavigation = true, hasLogo = true }: LayoutProps) => {
    // if user is unauthorized, redirect to login

    return (
        <main className="container relative h-full max-w-md mx-auto bg-white">
            {hasLogo && (
                <div className="flex px-5 pt-3">
                    <Logo className="w-16" />
                </div>
            )}

            <Outlet />

            {hasNavigation && <BottomNavigation />}
        </main>
    );
};

export default Layout;
