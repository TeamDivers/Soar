import moment from 'moment';
import React from 'react';

import Dot from '@components/Dot';

import { text2Color } from '@utils/color';

import { RecordType } from '@interfaces/record';

interface RecordCardProps {
    record: RecordType;
}

const RecordCard = ({ record }: RecordCardProps) => {
    return (
        <div className="flex items-center justify-between w-full shadow-md rounded-[10px] py-[18px] px-4">
            <div className="flex items-center gap-3">
                <Dot color={text2Color(record.tagName)} size={24} />
                <span className="text-base font-semibold text-black">
                    {record.tagName}
                </span>
            </div>
            <span className="text-sm font-medium text-neutral-400">
                {moment(record.startDate).format('YYYY.MM.DD H:MM')}-
                {moment(record.endDate).format('H:MM')}
            </span>
        </div>
    );
};

export default RecordCard;
