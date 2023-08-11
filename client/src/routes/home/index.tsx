import React from 'react';
import { Link } from 'react-router-dom';

import BottomSheet from '@components/BottomSheet';

import useBottomSheet from '@hooks/useBottomSheet';

const Home = () => {
    const { isOpen, toggle, close } = useBottomSheet();

    return (
        <div className="flex flex-col pt-8">
            <div className="bg-white rounded-[20px] pt-10 px-[26px] shadow-md pb-4">
                Hi
            </div>
        </div>
    );
};

export default Home;
