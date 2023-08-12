import React from 'react';
import { Outlet } from 'react-router-dom';

import BottomNavigation from '@components/BottomNavigation';

import { Logo } from '../../assets/images';

interface LayoutProps {
    hasNavigation?: boolean;
    hasLogo?: boolean;
    hasPadding?: boolean;
}

const Layout = ({
    hasNavigation = true,
    hasLogo = true,
    hasPadding = true
}: LayoutProps) => {
    // if user is unauthorized, redirect to login

    return (
        <main className="container relative h-full max-w-md mx-auto bg-white">
            <div className={`${hasPadding && 'px-5 pt-3'}`}>
                {hasLogo && (
                    <div className="flex">
                        <Logo className="w-16" />
                    </div>
                )}

                <Outlet />
            </div>
            {hasNavigation && <BottomNavigation />}
        </main>
    );
};

export default Layout;
