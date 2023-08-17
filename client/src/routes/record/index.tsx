import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { useGetRecords } from '@apis/record/getRecords';

import CalendarView from '@components/CalendarView';
import Dot from '@components/Dot';
import RecordCard from '@components/RecordCard';
import RoundButton from '@components/RoundButton';
import SlideToDelete from '@components/SlideToDelete';
import SortSelector from '@components/SortSelector';
import SwitchSelector from '@components/SwitchSelector';

import { getMemberId } from '@utils/auth';
import { text2Color } from '@utils/color';

import { RecordType } from '@interfaces/record';

const VIEWS = ['캘린더', '리스트'];

const Record = () => {
    const navigate = useNavigate();
    const [view, setView] = useState(VIEWS[0]);
    const [day, setDay] = useState(new Date());

    const { data } = useGetRecords({ memberId: parseInt(getMemberId()) });

    const handleOnClickCreateRecord = () => {
        navigate('/record/create');
    };

    if (!data) {
        return <></>;
    }

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
                            {[
                                ...new Set(
                                    data.map((item: RecordType) => item.tagName)
                                )
                            ].map((v) => {
                                /** TODO: 중복 방지 */
                                const color = text2Color(v);
                                return (
                                    <div
                                        key={v}
                                        className="flex gap-[6px] items-center"
                                    >
                                        <Dot color={color} size={8} />
                                        <div className="text-xs font-medium text-neutral-400">
                                            {v}
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
            <div
                className="fixed bottom-[120px] px-4 max-w-md"
                style={{ width: '-webkit-fill-available' }}
            >
                <RoundButton onClick={handleOnClickCreateRecord}>
                    <div className="text-lg font-semibold text-white py-[14px]">
                        학습기록 작성하기
                    </div>
                </RoundButton>
            </div>
        </div>
    );
};

export default Record;

const ListView = ({ data }: { data: RecordType[] }) => {
    return (
        <div className="flex flex-col mt-4">
            <div className="flex justify-between px-4 mt-3 mb-2">
                <h1 className="text-xl font-bold text-black">나의 학습기록</h1>
                <SortSelector onChange={(v: string) => console.log(v)} />
            </div>
            <div>
                {data.map((v: RecordType) => {
                    return (
                        <SlideToDelete
                            key={v.id}
                            onDelete={function (): void {
                                throw new Error('Function not implemented.');
                            }}
                        >
                            <div className="px-4 py-2">
                                <RecordCard record={v} />
                            </div>
                        </SlideToDelete>
                    );
                })}
            </div>
        </div>
    );
};
