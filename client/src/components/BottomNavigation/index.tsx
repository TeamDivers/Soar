import React, { useEffect, useState } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';

import { menus } from '@constants/menus';

const BottomNavigation = () => {
    const navigate = useNavigate();

    const [activeTab, setActiveTab] = useState(menus[0]);

    const handleTabChange = (menu: (typeof menus)[number]) => {
        setActiveTab(menu);
    };

    return (
        <div className="fixed bottom-0 z-50 w-full max-w-md mx-auto bg-white drop-shadow">
            <div className="flex justify-between pt-[14px] pb-[34px] px-[52px]">
                {menus.map((menu) => {
                    const Icon = menu.Icon;
                    return (
                        <NavLink
                            key={menu.title}
                            to={menu.route}
                            className="flex flex-col gap-[6px] items-center justify-center"
                            onClick={() => handleTabChange(menu)}
                        >
                            {({ isActive, isPending }) => (
                                <>
                                    <Icon
                                        color={isActive ? '#1D5CFF' : 'gray'}
                                    />
                                    <span
                                        className={`text-center text-xs font-medium ${
                                            isActive
                                                ? 'text-primary'
                                                : 'text-gray-500'
                                        }`}
                                    >
                                        {menu.title}
                                    </span>
                                </>
                            )}
                        </NavLink>
                    );
                })}
            </div>
        </div>
    );
};

export default BottomNavigation;
