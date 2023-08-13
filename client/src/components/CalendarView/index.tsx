import moment from 'moment';
import React, { useState } from 'react';
import { Calendar } from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import './index.css';

import Dot from '@components/Dot';
import Modal from '@components/Modal';

import { LeftSm, RightSm } from '@images/index';

import { WEEKDAY } from '@constants/calendar';

import { text2Color } from '@utils/color';

interface CalendarViewProps {
    data: any[];
    value: any;
    setValue: any;
}

const CalendarView = ({ data, value, setValue }: CalendarViewProps) => {
    const [isOpen, setIsOpen] = useState(false);
    const [selected, setSelected] = useState<{
        date: Date | undefined;
        data: any[];
    }>({ date: undefined, data: [] });

    const close = () => setIsOpen(false);

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

    const handleOnClickDay = (date: Date) => {
        const selected = data.filter((v) =>
            moment(v.startDate).isSame(date, 'day')
        );

        if (selected.length > 0) {
            setIsOpen(true);
            setSelected({
                date: date,
                data: selected
            });
        }
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
                formatDay={(locale, date) => moment(date).format('D')}
                formatShortWeekday={(locale, date) =>
                    WEEKDAY[parseInt(moment(date).format('d'))]
                }
                value={value}
                onClickDay={handleOnClickDay}
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
            <Modal isOpen={isOpen} close={close}>
                <div className="w-screen px-5">
                    <div className="pl-2 mb-4 text-2xl font-bold text-white">
                        {moment(selected.date).format('YYYY년 M월 D일')}
                    </div>
                    <div className="flex flex-col gap-6 px-5 py-10 bg-white rounded-[20px] shadow h-[400px]">
                        {selected.data.map((v) => {
                            return (
                                <div key={v.id} className="flex gap-3">
                                    <Dot
                                        color={text2Color(v.category)}
                                        size={24}
                                    />
                                    <div className="flex flex-col">
                                        <span className="text-base font-semibold text-black">
                                            {v.category}
                                        </span>
                                        <span className="text-xs font-medium text-neutral-400">
                                            {moment(v.startDate).format(
                                                'MMM D H:mm'
                                            )}
                                            -{moment(v.endDate).format('H:mm')}
                                        </span>
                                    </div>
                                </div>
                            );
                        })}
                    </div>
                </div>
            </Modal>
        </div>
    );
};

export default CalendarView;
