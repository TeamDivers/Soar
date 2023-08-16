import React from 'react';
import { useNavigate } from 'react-router-dom';

import Divider from '@components/Divider';
import RoundButton from '@components/RoundButton';

import { Pencil } from '@images/index';

const Home = () => {
    const navigate = useNavigate();

    return (
        <div className="flex flex-col px-4 pt-5">
            <div className="bg-white rounded-[20px] pt-10 px-[26px] shadow-md pb-4">
                <div className="flex justify-between pb-[22px]">
                    <div>
                        <div className="pb-2 text-lg font-bold text-black">
                            전진호님이
                            <br />
                            만들 수 있는 포트폴리오가
                            <br />
                            4개 이상이에요!
                        </div>
                        <RoundButton
                            onClick={() => navigate('/portfolio/create')}
                        >
                            포트폴리오 만들기
                        </RoundButton>
                    </div>
                    <Pencil className="w-[100px]" />
                    {/* TODO: size of pencil logo will be change!! */}
                </div>
                <Divider isFull />
                <div className="flex justify-around itmes-center pt-[14px]">
                    <div className="flex flex-col items-center">
                        <span className="text-xs font-medium text-zinc-500">
                            내 학습기록
                        </span>
                        <span className="text-lg font-bold text-blue-600">
                            4
                        </span>
                    </div>
                    <Divider direction="vertical" length={30} />
                    <div className="flex flex-col items-center ">
                        <span className="text-xs font-medium text-zinc-500">
                            보유 포트폴리오
                        </span>
                        <span className="text-lg font-bold text-blue-600">
                            7
                        </span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Home;
