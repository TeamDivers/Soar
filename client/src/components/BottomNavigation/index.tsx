import React, { useState } from 'react';

import { menus } from '@constants/menus';

const BottomNavigation = () => {
    const [activeTab, setActiveTab] = useState(menus[0].title);

    const handleTabChange = (tab: string) => {
        setActiveTab(tab);

        /**
         * TODO: Link to tab
         * */
    };

    return (
        <div className="fixed bottom-0 w-full max-w-md mx-auto bg-white drop-shadow">
            <div className="flex justify-between pt-[14px] pb-[34px] px-[52px]">
                {menus.map((menu) => {
                    return (
                        <div
                            key={menu.title}
                            className={`text-center text-xs font-medium ${
                                activeTab === menu.title
                                    ? 'text-primary'
                                    : 'text-gray-500'
                            }`}
                            onClick={() => handleTabChange(menu.title)}
                        >
                            {menu.icon('#1D5CFF')}
                            <span>{menu.title}</span>
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default BottomNavigation;
