import moment from 'moment';
import React from 'react';
import { Calendar } from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import './index.css';

import { LeftSm, RightSm } from '@images/index';

import { WEEKDAY } from '@constants/calendar';

import { text2Color } from '@utils/color';

interface CalendarViewProps {
    data: any[];
    onChange: () => void;
    value: any;
    setValue: any;
}

const CalendarView = ({
    data,
    onChange,
    value,
    setValue
}: CalendarViewProps) => {
    const onClickPrevMonth = () => {
        const previousMonthDate = new Date(value);
        previousMonthDate.setMonth(value.getMonth() - 1);
        setValue(previousMonthDate);
    };

    const onClickNextMonth = () => {
        const previousMonthDate = new Date(value);
        previousMonthDate.setMonth(value.getMonth() + 1);
        setValue(previousMonthDate);
    };

    return (
        <div className="mt-[40px] mb-20">
            <div className="flex items-center justify-between px-8 mb-8">
                <span className="text-xl font-extrabold text-zinc-800">
                    {moment(value).format('YYYY년 M월')}
                </span>
                <div className="flex gap-4">
                    <button onClick={onClickPrevMonth}>
                        <LeftSm className="w-5 h-5 p-1" />
                    </button>
                    <button onClick={onClickNextMonth}>
                        <RightSm className="w-5 h-5 p-1" />
                    </button>
                </div>
            </div>
            <Calendar
                onChange={onChange}
                formatDay={(locale, date) => moment(date).format('D')}
                formatShortWeekday={(locale, date) =>
                    WEEKDAY[parseInt(moment(date).format('d'))]
                }
                value={value}
                minDetail="month"
                maxDetail="month"
                showNeighboringMonth={false}
                showNavigation={false}
                className="w-full px-5 mx-auto"
                tileContent={({ date, view }) => {
                    return (
                        <div className="flex items-center justify-center h-3 gap-[2px]">
                            {data.map((v) => {
                                if (moment(v.startDate).isSame(date, 'day')) {
                                    const color = text2Color(v.category);
                                    return (
                                        <div
                                            key={v.id}
                                            className="w-2 h-2 rounded-full"
                                            style={{ backgroundColor: color }}
                                        ></div>
                                    );
                                }
                            })}
                        </div>
                    );
                }}
            />
        </div>
    );
};

export default CalendarView;
