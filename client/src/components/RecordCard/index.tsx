import React from 'react';

import Dot from '@components/Dot';

import { text2Color } from '@utils/color';

interface RecordCardProps {}

const RecordCard = ({}: RecordCardProps) => {
    return (
        <div className="flex items-center justify-between w-full shadow-md rounded-[10px] py-[18px] px-4">
            <div className="flex items-center gap-3">
                <Dot color={text2Color('수학')} size={24} />
                <span className="text-base font-semibold text-black">수학</span>
            </div>
            <span className="text-sm font-medium text-neutral-400">
                2023.08.08 8:00-14:00
            </span>
        </div>
    );
};

export default RecordCard;
