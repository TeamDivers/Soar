import React, { useState } from 'react';

import { RecordType } from 'src/apis/record/getRecords';

import CalendarView from '@components/CalendarView';
import Dot from '@components/Dot';
import RecordCard from '@components/RecordCard';
import RoundButton from '@components/RoundButton';
import SlideToDelete from '@components/SlideToDelete';
import SortSelector from '@components/SortSelector';
import SwitchSelector from '@components/SwitchSelector';

import { text2Color } from '@utils/color';

const VIEWS = ['캘린더', '리스트'];

const Record = () => {
    const [view, setView] = useState(VIEWS[0]);
    const [day, setDay] = useState(new Date());

    const data = [
        {
            id: 1,
            category: '수학',
            startDate: new Date(2023, 7, 13, 10, 0),
            endDate: new Date(2023, 7, 13, 14, 0)
        },
        {
            id: 2,
            category: '코딩',
            startDate: new Date(2023, 7, 15, 10, 0),
            endDate: new Date(2023, 7, 15, 14, 0)
        },
        {
            id: 3,
            category: '과학',
            startDate: new Date(2023, 7, 15, 14, 0),
            endDate: new Date(2023, 7, 15, 18, 0)
        },
        {
            id: 4,
            category: '수학',
            startDate: new Date(2023, 7, 22, 14, 0),
            endDate: new Date(2023, 7, 22, 18, 0)
        },
        {
            id: 5,
            category: '영어',
            startDate: new Date(2023, 7, 13, 14, 0),
            endDate: new Date(2023, 7, 13, 18, 0)
        }
    ];

    return (
        <div className="flex flex-col pt-[20px]">
            <div className="mx-auto">
                <SwitchSelector
                    options={VIEWS}
                    onChange={(selectedOption: string) => {
                        setView(selectedOption);
                    }}
                />
            </div>
            {view === '캘린더' ? (
                <div>
                    <CalendarView data={data} value={day} setValue={setDay} />
                    <div className="absolute w-full px-4 bottom-[188px]">
                        <div className="grid grid-cols-3 rounded-[10px] border border-neutral-300 p-5 gap-3">
                            {data.map((v) => {
                                /** TODO: 중복 방지 */
                                const color = text2Color(v.category);
                                return (
                                    <div
                                        key={v.id}
                                        className="flex gap-[6px] items-center"
                                    >
                                        <Dot color={color} size={8} />
                                        <div className="text-xs font-medium text-neutral-400">
                                            {v.category}
                                        </div>
                                    </div>
                                );
                            })}
                        </div>
                    </div>
                </div>
            ) : (
                <ListView data={data} />
            )}
            <div className="fixed bottom-[120px] w-full px-4 mx-auto">
                <RoundButton
                    onClick={function (): void {
                        throw new Error('Function not implemented.');
                    }}
                >
                    <div className="text-lg font-semibold text-white">
                        학습기록 작성하기
                    </div>
                </RoundButton>
            </div>
        </div>
    );
};

export default Record;

const ListView = ({ data }: { data: any }) => {
    return (
        <div className="flex flex-col mt-4">
            <div className="flex justify-between px-4 mt-3 mb-2">
                <h1 className="text-xl font-bold text-black">나의 학습기록</h1>
                <SortSelector onChange={(v: string) => console.log(v)} />
            </div>
            <div>
                {[
                    ...new Set(data.map((item: RecordType) => item.category))
                ].map((v: any) => {
                    return (
                        <SlideToDelete
                            key={v.id}
                            onDelete={function (): void {
                                throw new Error('Function not implemented.');
                            }}
                        >
                            <div className="px-4 py-2">
                                <RecordCard />
                            </div>
                        </SlideToDelete>
                    );
                })}
            </div>
        </div>
    );
};
