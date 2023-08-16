import React from 'react';
import { useNavigate } from 'react-router-dom';

import RoundButton from '@components/RoundButton';

import OnBoardImg from '../../assets/images/onboard.png';

const OnBoard = () => {
    const navigate = useNavigate();

    const handleStartButton = () => {
        navigate('/');
    };

    return (
        <div className="flex flex-col pt-5">
            <img src={OnBoardImg} className="max-h-[463px] object-fit" />
            <h1 className="text-neutral-700 text-[26px] font-normal mx-auto text-center mt-10 mb-[10px] leading-[31px]">
                {/* SB AggroOTF */}
                단순 공부 뿐만 아닌, <br />
                포트폴리오까지! <br />
                공부도 효율적인 MZ답게
            </h1>
            <h3 className="text-neutral-500 text-[13px] font-normal mx-auto text-center">
                내가 관심있게 공부하고 있는 분야를 쉽고 간단하게 기록하고 <br />
                검증된 포트폴리오로 만들어보세요!
            </h3>
            <div className="fixed max-w-md mx-auto bottom-[40px] w-full px-4">
                <RoundButton onClick={handleStartButton}>
                    <div className="py-[13px] text-white text-xl font-bold leading-[24px]">
                        시작하기
                    </div>
                </RoundButton>
            </div>
        </div>
    );
};

export default OnBoard;
