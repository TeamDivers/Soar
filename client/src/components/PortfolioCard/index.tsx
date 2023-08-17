import moment from 'moment';
import React, { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { Star } from '@images/index';

import { PortfolioType } from '@interfaces/portfolio';

interface PortfolioCardProps {
    portfolio: PortfolioType;
    ranking?: number;
    rating?: number;
    size?: 'sm' | 'lg';
}

<div className="w-[46px] h-[22px] relative bg-blue-600 rounded-[11px]">
    <div className="left-[8px] top-[5px] absolute text-center text-white text-xs font-normal">
        24위
    </div>
</div>;

const PortfolioCard = ({
    portfolio,
    rating,
    ranking,
    size = 'lg'
}: PortfolioCardProps) => {
    const navigate = useNavigate();

    const imageSize = size === 'lg' ? 160 : 100;

    return (
        <div
            className="relative"
            onClick={() => navigate(`/portfolio/${portfolio.portfolioId}`)}
        >
            {ranking && (
                <div className="aggro absolute left-2 top-2 bg-blue-600 rounded-[11px] text-white text-xs font-normal pt-[5px] px-2 pb-[3px] z-10">
                    {/* SB AggroOTF */}
                    {ranking}위
                </div>
            )}
            <div className="flex shadow-md rounded-[10px]">
                <img
                    src={portfolio?.thumbnailURL}
                    className="rounded-l-[10px]"
                    style={{ width: imageSize, height: imageSize }}
                />
                <div
                    className={`${
                        size === 'lg' ? 'p-4 h-[160px]' : 'py-[10px] px-3'
                    } flex flex-col`}
                >
                    <div className="flex items-center justify-between">
                        <div
                            className={`${
                                size === 'lg'
                                    ? 'text-xl mb-[10px] leading-[23.87px]'
                                    : 'text-base mb-[6px] leading-[19px]'
                            } font-bold text-black`}
                        >
                            {portfolio.title}
                        </div>
                        {rating && (
                            <div className="flex gap-1 mb-1 text-xs font-medium text-neutral-500">
                                <Star fill="#FFA800" />
                                {rating}
                            </div>
                        )}
                    </div>
                    <div
                        className={`${
                            size === 'lg'
                                ? 'mb-[12px] truncate-4'
                                : 'mb-[7px] truncate-2'
                        } text-sm font-normal text-zinc-800 leading-[16.5px]`}
                    >
                        {portfolio.description}
                    </div>
                    <div className="text-xs font-normal text-neutral-400 leading-[14.32px]">
                        {moment(
                            portfolio.projects[0]?.startDate || new Date()
                        ).format('YYYY.MM.DD')}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default PortfolioCard;
