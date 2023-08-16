import moment from 'moment';
import React from 'react';

import Dot from '@components/Dot';

import { text2Color } from '@utils/color';

interface ProjectInterface {
    title: string;
    category: string;
    startDate: string;
    endDate: string;
}

const Project = ({ title, category, startDate, endDate }: ProjectInterface) => {
    return (
        <div className="flex items-center justify-between px-4 py-6 shadow-md rounded-[10px]">
            <div className="flex items-center gap-[14px]">
                <span className="text-lg font-semibold text-black">
                    {title}
                </span>
                <div className="flex items-center gap-1">
                    <Dot color={text2Color(category)} size={12} />
                    <span className="text-sm font-medium text-neutral-400">
                        {category}
                    </span>
                </div>
            </div>
            <div className="flex flex-col text-sm font-medium text-neutral-400">
                <div className="text-end">
                    {moment(startDate).format('YYYY.MM.DD')}-
                    {moment(endDate).format('YYYY.MM.DD')}
                </div>
                <div className="text-end">8:00am-14:00pm</div>
            </div>
        </div>
    );
};

export default Project;
