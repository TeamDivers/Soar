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
        <div className="fixed bottom-0 w-full max-w-md p-2 mx-auto bg-gray-900 border-t border-gray-800">
            <div className="flex justify-around">
                {menus.map((menu) => {
                    return (
                        <button
                            key={menu.title}
                            className={`p-2 ${
                                activeTab === menu.title
                                    ? 'text-blue-500'
                                    : 'text-gray-400'
                            }`}
                            onClick={() => handleTabChange(menu.title)}
                        >
                            {menu.title}
                        </button>
                    );
                })}
            </div>
        </div>
    );
};

export default BottomNavigation;
