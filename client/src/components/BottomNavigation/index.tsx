import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { menus } from '@constants/menus';

const BottomNavigation = () => {
    const navigate = useNavigate();

    const [activeTab, setActiveTab] = useState(menus[0].title);

    const handleTabChange = (menu: (typeof menus)[number]) => {
        setActiveTab(menu.title);
        navigate(menu.route);
    };

    return (
        <div className="fixed bottom-0 w-full max-w-md mx-auto bg-white drop-shadow">
            <div className="flex justify-between pt-[14px] pb-[34px] px-[52px]">
                {menus.map((menu) => {
                    const Icon = menu.Icon;
                    return (
                        <div
                            key={menu.title}
                            className="flex flex-col gap-[6px] items-center justify-center"
                            onClick={() => handleTabChange(menu)}
                        >
                            <Icon
                                color={
                                    activeTab === menu.title
                                        ? '#1D5CFF'
                                        : 'gray'
                                }
                            />

                            <span
                                className={`text-center text-xs font-medium ${
                                    activeTab === menu.title
                                        ? 'text-primary'
                                        : 'text-gray-500'
                                }`}
                            >
                                {menu.title}
                            </span>
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default BottomNavigation;
