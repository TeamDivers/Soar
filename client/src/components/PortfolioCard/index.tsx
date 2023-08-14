import React from 'react';

interface PortfolioCardProps {}

const PortfolioCard = ({}: PortfolioCardProps) => {
    return (
        <div className="flex w-full h-40 shadow-md rounded-[10px]">
            <img src="https://placehold.co/400" className="rounded-l-[10px]" />
            <div className="flex flex-col p-4 ">
                <div className="text-xl font-bold text-black mb-[10px]">
                    iOS 개발자입니다
                </div>
                <div className="text-sm font-normal text-zinc-800 mb-[12px] truncate-lines">
                    안녕하세요 개발을 사랑..하는 학생 전!! 지노예요~~ 안녕하세요
                    개발을 사랑..하는 학생 전!! 지노예요~~ ...
                </div>
                <div className="text-xs font-normal text-neutral-400">
                    만든 날짜: 2023.08.12
                </div>
            </div>
        </div>
    );
};

export default PortfolioCard;
