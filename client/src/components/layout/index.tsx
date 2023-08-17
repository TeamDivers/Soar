import React from 'react';
import { Outlet } from 'react-router-dom';

import { useGetMember } from '@apis/member/getMember';

import BottomNavigation from '@components/BottomNavigation';

import { getMemberId } from '@utils/auth';

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
    const { data: member } = useGetMember({
        memberId: parseInt(getMemberId())
    });

    return (
        <main
            className={`container relative h-screen max-w-md mx-auto bg-white overflow-y-scroll hide-scrollbar`}
        >
            {hasHeader && (
                <div className="flex items-center justify-between h-10 px-5 pt-3 text-xl font-bold text-black">
                    {title || <Logo className="w-16" />}
                    {hasProfile ? (
                        <img
                            src={
                                member?.thumbnail ||
                                'https://placehold.co/200?text=jjh'
                            }
                            className="w-10 h-10 rounded-full"
                        />
                    ) : (
                        right()
                    )}
                </div>
            )}
            <div className={`${hasNavigation ? 'mb-[100px]' : ''}`}>
                {children || <Outlet />}
            </div>

            {hasNavigation && <BottomNavigation />}
        </main>
    );
};

export default Layout;
