import React from 'react';
import { useNavigate } from 'react-router-dom';

import RoundButton from '@components/RoundButton';

const OnBoard = () => {
    const navigate = useNavigate();

    const handleStartButton = () => {
        navigate('/');
    };

    return (
        <div className="flex flex-col pt-5">
            <div className="fixed max-w-md mx-auto bottom-[40px] w-full px-4">
                <RoundButton onClick={handleStartButton}>시작하기</RoundButton>
            </div>
        </div>
    );
};

export default OnBoard;
