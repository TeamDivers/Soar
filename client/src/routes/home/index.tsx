import React from 'react';
import { Link } from 'react-router-dom';

import BottomSheet from '@components/BottomSheet';

import useBottomSheet from '@hooks/useBottomSheet';

const Home = () => {
    const { isOpen, toggle, close } = useBottomSheet();

    return <div className=""></div>;
};

export default Home;
