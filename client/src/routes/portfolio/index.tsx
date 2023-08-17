import React from 'react';
import { useNavigate } from 'react-router-dom';

import { useGetPortfolios } from '@apis/portfolio/getPortfolio';

import PortfolioCard from '@components/PortfolioCard';
import SlideToDelete from '@components/SlideToDelete';
import SortSelector from '@components/SortSelector';

import { Plus } from '@images/index';

import { getMemberId } from '@utils/auth';

const Portfolio = () => {
    const navigate = useNavigate();

    const { data: portfolios } = useGetPortfolios({
        memberId: parseInt(getMemberId())
    });

    const onDeletePortfolio = () => {};

    const onClickPlusButton = () => {
        navigate('/portfolio/create');
    };

    return (
        <div className="overflow-y-scroll">
            <div className="flex justify-between px-4 mt-[34px] mb-2">
                <h1 className="text-xl font-bold text-black">
                    나의 포트폴리오
                </h1>
                <SortSelector onChange={(v: string) => console.log(v)} />
            </div>
            <div className="">
                {portfolios?.map((portfolio) => {
                    return (
                        <SlideToDelete
                            onDelete={onDeletePortfolio}
                            key={portfolio.portfolioId}
                        >
                            <div className="px-4 py-2">
                                <PortfolioCard portfolio={portfolio} />
                            </div>
                        </SlideToDelete>
                    );
                })}
            </div>
            <div
                className="flex fixed bottom-[118px] max-w-md justify-end pr-4 z-50"
                style={{ width: '-webkit-fill-available' }}
            >
                <button
                    className="p-4 rounded-full bg-primary"
                    type="button"
                    onClick={onClickPlusButton}
                >
                    <Plus />
                </button>
            </div>
        </div>
    );
};

export default Portfolio;
