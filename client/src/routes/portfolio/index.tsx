import React from 'react';

import PortfolioCard from '@components/PortfolioCard';
import SlideToDelete from '@components/SlideToDelete';
import SortSelector from '@components/SortSelector';

const Portfolio = () => {
    const deletePortfolio = () => {};

    return (
        <>
            <div className="flex justify-between px-4 mt-[34px] mb-2">
                <h1 className="text-xl font-bold text-black">
                    나의 포트폴리오
                </h1>
                <SortSelector onChange={(v: string) => console.log(v)} />
            </div>
            <SlideToDelete onDelete={deletePortfolio}>
                <div className="px-4 py-2">
                    <PortfolioCard />
                </div>
            </SlideToDelete>
        </>
    );
};

export default Portfolio;
