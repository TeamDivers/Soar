import React from 'react';
import { Outlet } from 'react-router-dom';

import BottomNavigation from '@components/BottomNavigation';

import { Logo } from '../../assets/images';

interface LayoutProps {
    hasNavigation?: boolean;
    hasHeader?: boolean;
    hasProfile?: boolean;
    children?: React.ReactNode;
    title?: string;
    right?: () => React.ReactNode;
}

const Layout = ({
    hasNavigation = true,
    hasHeader = true,
    hasProfile = false,
    title,
    right = () => <></>,
    children
}: LayoutProps) => {
    // if user is unauthorized, redirect to login

    return (
        <main
            className={`container relative h-full max-w-md mx-auto bg-white overflow-hidden`}
        >
            {hasHeader && (
                <div className="flex items-center justify-between h-10 px-5 pt-3">
                    {title || <Logo className="w-16" />}
                    {hasProfile ? (
                        <img
                            src="https://placehold.co/400"
                            className="w-10 h-10 rounded-full"
                        />
                    ) : (
                        right()
                    )}
                </div>
            )}
            <div className={`${hasNavigation ? 'pb-[94px]' : ''}`}>
                {children || <Outlet />}
            </div>

            {hasNavigation && <BottomNavigation />}
        </main>
    );
};

export default Layout;
