import React, { useEffect, useRef, useState } from 'react';

interface PortfolioCardProps {
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
    rating,
    ranking,
    size = 'lg'
}: PortfolioCardProps) => {
    const [height, setHeight] = useState(0);
    const ref = useRef<HTMLDivElement | null>(null);

    useEffect(() => {
        setHeight(ref.current?.clientHeight || 0);
    }, [ref]);

    return (
        <div className="relative">
            {ranking && (
                <div className="absolute left-2 top-2 bg-blue-600 rounded-[11px] text-white text-xs font-normal pt-[5px] px-2 pb-[3px]">
                    {/* SB AggroOTF */}
                    {ranking}위
                </div>
            )}
            <div className="relative flex shadow-md rounded-[10px]">
                <div>
                    <img
                        src="https://placehold.co/400"
                        className="rounded-l-[10px] w-full h-full aspect-square"
                        style={{
                            width: height,
                            height
                        }}
                    />
                </div>
                <div
                    ref={ref}
                    className={`${
                        size === 'lg' ? 'p-4 h-[160px]' : 'py-[10px] px-3'
                    } flex flex-col flex-1 h-fit`}
                >
                    <div className="flex justify-between">
                        <div
                            className={`${
                                size === 'lg'
                                    ? 'text-xl mb-[10px]'
                                    : 'text-base mb-[6px]'
                            } font-bold text-black`}
                        >
                            iOS 개발자입다
                        </div>
                        {rating && <div>{rating}</div>}
                    </div>
                    <div
                        className={`${
                            size === 'lg'
                                ? 'mb-[12px] truncate-4'
                                : 'mb-[7px] truncate-2'
                        } text-sm font-normal text-zinc-800`}
                    >
                        안녕하세요 개발을 사랑..하는 학생 전!! 지노예요~~
                        안녕하세요 개발을 사랑..하는 학생 전!! 지노예요~~ ...
                        안녕하세요 개발을 사랑..하는 학생 전!! 지노예요~~
                        안녕하세요 개발을 사랑..하는 학생 전!! 지노예요~~ ...
                    </div>
                    <div className="text-xs font-normal text-neutral-400">
                        만든 날짜: 2023.08.12
                    </div>
                </div>
            </div>
        </div>
    );
};

export default PortfolioCard;
