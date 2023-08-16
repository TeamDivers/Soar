import React from 'react';
import { useNavigate } from 'react-router-dom';

import { useGetPortfolios } from '@apis/portfolio/getPortfolio';
import { useGetRecords } from '@apis/record/getRecords';

import Divider from '@components/Divider';
import PortfolioCard from '@components/PortfolioCard';
import RoundButton from '@components/RoundButton';

import { Pencil } from '@images/index';

import { getMemberId } from '@utils/auth';

const Home = () => {
    const navigate = useNavigate();

    const { data: records } = useGetRecords({
        memberId: parseInt(getMemberId())
    });
    const { data: portfolios } = useGetPortfolios({
        memberId: parseInt(getMemberId())
    });
    const { data: ranks } = useGetPortfolios({
        option: 'rank'
    });

    return (
        <div className="flex flex-col px-4 pt-5">
            <div className="mb-[40px] bg-white rounded-[20px] pt-10 px-[26px] shadow-md pb-4">
                <div className="flex justify-between pb-[22px]">
                    <div>
                        <div className="pb-[17px] text-lg font-normal text-black aggro leading-[21.58px]">
                            전진호님이
                            <br />
                            만들 수 있는 포트폴리오가
                            <br />
                            {records?.length}개 이상이에요!
                        </div>
                        <RoundButton
                            onClick={() => navigate('/portfolio/create')}
                        >
                            <div className="leading-[19.09px] py-[12px] text-white text-base font-bold">
                                포트폴리오 만들기
                            </div>
                        </RoundButton>
                    </div>
                    <Pencil className="w-[144px]" />
                    {/* TODO: size of pencil logo will be change!! */}
                </div>
                <Divider isFull />
                <div className="flex justify-around itmes-center pt-[14px]">
                    <div className="flex flex-col items-center">
                        <span className="text-xs font-medium text-zinc-500">
                            내 학습기록
                        </span>
                        <span className="text-lg font-bold text-blue-600">
                            {records?.length}
                        </span>
                    </div>
                    <Divider direction="vertical" length={30} />
                    <div className="flex flex-col items-center ">
                        <span className="text-xs font-medium text-zinc-500">
                            보유 포트폴리오
                        </span>
                        <span className="text-lg font-bold text-blue-600">
                            {portfolios?.length}
                        </span>
                    </div>
                </div>
            </div>
            <div className="mb-4 text-xl font-bold text-black">
                오늘의 인기 포트폴리오 Top5
            </div>
            <div className="flex flex-col gap-3">
                {ranks?.map((portfolio) => {
                    return (
                        <PortfolioCard
                            key={portfolio.portfolioId}
                            portfolio={portfolio}
                        />
                    );
                })}
            </div>
        </div>
    );
};

export default Home;
