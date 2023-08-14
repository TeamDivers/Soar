import React from 'react';
import { Outlet } from 'react-router-dom';

import BottomNavigation from '@components/BottomNavigation';

import { Logo } from '../../assets/images';

interface LayoutProps {
    hasNavigation?: boolean;
    hasHeader?: boolean;
    hasProfile?: boolean;
    children?: React.ReactNode;
}

const Layout = ({
    hasNavigation = true,
    hasHeader = true,
    hasProfile = false,
    children
}: LayoutProps) => {
    // if user is unauthorized, redirect to login

    return (
        <main className={`container relative h-full max-w-md mx-auto bg-white`}>
            {hasHeader && (
                <div className="flex px-5 pt-3">
                    <Logo className="w-16" />
                </div>
            )}
            <div className={`${hasNavigation ? 'pb-28' : ''}`}>
                {children || <Outlet />}
            </div>

            {hasNavigation && <BottomNavigation />}
        </main>
    );
};

export default Layout;
