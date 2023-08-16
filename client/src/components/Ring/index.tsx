import React from 'react';

import { Crown } from '@images/index';

import { hexToRgba } from '@utils/color';

interface RingProps {
    size: 'md' | 'lg';
    rank?: number;
    color: string;
    img: string;
}

const Ring = ({ size, rank, color, img }: RingProps) => {
    const imgSize = size === 'md' ? 80 : 120;

    return (
        <div className="relative">
            {rank === 1 && (
                <Crown className="absolute transform -translate-x-1/2 rounded-full -top-7 left-1/2" />
            )}
            <img
                src={img}
                className="border-2 rounded-full"
                style={{
                    width: imgSize,
                    height: imgSize,
                    borderColor: color,
                    boxShadow: `0 0px 24px ${hexToRgba(color, 0.7)}`
                }}
            />
            {rank && (
                <div
                    className="absolute transform -translate-x-1/2 rounded-full left-1/2 w-[22px] h-[22px] flex justify-center items-center text-white text-[10px] font-normal bottom-[-11px]"
                    style={{ backgroundColor: color }}
                >
                    {/* SB AggroOTF */}
                    {rank}
                </div>
            )}
        </div>
    );
};

export default Ring;
