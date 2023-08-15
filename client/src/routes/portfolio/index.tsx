import React from 'react';
import { useNavigate } from 'react-router-dom';

import PortfolioCard from '@components/PortfolioCard';
import SlideToDelete from '@components/SlideToDelete';
import SortSelector from '@components/SortSelector';

import { Plus } from '@images/index';

const Portfolio = () => {
    const navigate = useNavigate();
    const portfolios = [{ id: 1 }];

    const onDeletePortfolio = () => {};

    const onClickPlusButton = () => {
        navigate('/portfolio/create');
    };

    return (
        <>
            <div className="flex justify-between px-4 mt-[34px] mb-2">
                <h1 className="text-xl font-bold text-black">
                    나의 포트폴리오
                </h1>
                <SortSelector onChange={(v: string) => console.log(v)} />
            </div>
            <div>
                {portfolios.map((portfolio) => {
                    return (
                        <SlideToDelete
                            onDelete={onDeletePortfolio}
                            key={portfolio.id}
                        >
                            <div className="px-4 py-2">
                                <PortfolioCard />
                            </div>
                        </SlideToDelete>
                    );
                })}
            </div>
            <button
                className="fixed bottom-[118px] right-4 p-4 rounded-full bg-primary"
                type="button"
                onClick={onClickPlusButton}
            >
                <Plus />
            </button>
        </>
    );
};

export default Portfolio;
