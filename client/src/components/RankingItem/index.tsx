import React from 'react';

import { Asc, Desc, Star } from '@images/index';

interface RankingItem {
    rank: number;
    isAscending: boolean;
    thumbnail: string;
    title: string;
    desc: string;
    rating: number;
}

const RankingItem = (props: RankingItem) => {
    return (
        <div className="flex items-center justify-between w-full">
            <div className="flex items-center">
                {/* SB AggroOTF */}
                <span className="text-xs font-normal leading-snug text-neutral-600 mr-[6px] aggro">
                    {props.rank}
                </span>
                <div className="mr-6">
                    {props.isAscending ? <Asc /> : <Desc />}
                </div>
                <img
                    src={props.thumbnail}
                    className="w-[42px] h-[42px] rounded-full mr-[10px]"
                />
                <div className="flex flex-col">
                    <span className="text-base font-bold text-black">
                        {props.title}
                    </span>
                    <span className="text-xs font-normal text-neutral-400">
                        {props.desc}
                    </span>
                </div>
            </div>
            <div className="flex gap-1 mb-1 text-xs font-medium text-neutral-500">
                <Star />
                {props.rating}
            </div>
        </div>
    );
};

export default RankingItem;
