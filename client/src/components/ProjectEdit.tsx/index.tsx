import React, { useState } from 'react';

import { useGetRecords } from '@apis/record/getRecords';

import BottomSheet from '@components/BottomSheet';
import DateTimePicker from '@components/DateTimePicker';
import FileInput from '@components/FileInput';
import PlusButton from '@components/PlustButton';
import RecordCard from '@components/RecordCard';
import RoundButton from '@components/RoundButton';
import TextArea from '@components/TextArea';
import TextInput from '@components/TextInput';

import { Plus } from '@images/index';

import useBottomSheet from '@hooks/useBottomSheet';
import useTextInput from '@hooks/useTextInput';

interface ProejctEditProps {
    onAddProject: (proejct: any) => void;
}

const ProejctEdit = ({ onAddProject }: ProejctEditProps) => {
    const sheet = useBottomSheet();

    const records = useGetRecords({ memberId: 0 });

    const title = useTextInput();
    const content = useTextInput();
    const [selectedRecords, setSelectedRecords] = useState<any[]>([]);

    const selectRecord = (newRecord: any) => {
        setSelectedRecords((prev) => [...prev, newRecord]);
    };

    const handleOnClickAddRecord = () => {
        /** TODO: open bottomsheet for add record */
        sheet.toggle();
    };

    const handleOnClickAddProject = () => {
        onAddProject({ title });
    };

    return (
        <div className="flex flex-col px-4 py-6 shadow-md rounded-[10px] bg-white">
            <div className="mb-5">
                <Title title={'공부명'} />
                <TextInput
                    size="sm"
                    value={''}
                    placeholder="공부명을 입력해주세요"
                    onChange={function (value: string): void {
                        throw new Error('Function not implemented.');
                    }}
                />
            </div>
            <div className="mb-5">
                <Title title={'날짜 및 시간'} />
                <div className="flex justify-between gap-[6px] items-center">
                    <DateTimePicker value={undefined} onChange={undefined} />
                    <span className="text-base font-medium text-neutral-500">
                        ~
                    </span>
                    <DateTimePicker value={undefined} onChange={undefined} />
                </div>
            </div>
            <div className="mb-5">
                <Title title={'설명'} />
                <TextArea
                    value={''}
                    placeholder="공부에 대한 내용을 입력해주세요."
                    onChange={function (value: string): void {
                        throw new Error('Function not implemented.');
                    }}
                />
            </div>
            <div className="mb-5">
                <Title title={'학습기록 가져오기'} />
                <div className="flex flex-col gap-2 mb-2">
                    <RecordCard />
                    <RecordCard />
                    {selectedRecords.map((selectedRecord) => {
                        return <RecordCard key={selectedRecord.id} />;
                    })}
                </div>
                <PlusButton onClick={handleOnClickAddRecord} />
            </div>
            <div className="mb-5">
                <Title title={'공부 기록'} />
                <div className="flex flex-col gap-2 mb-2">
                    <FileInput label={'파일'} />
                </div>
            </div>
            <RoundButton onClick={handleOnClickAddProject}>
                <div className="py-[10px]">완료</div>
            </RoundButton>
            <BottomSheet
                isOpen={sheet.isOpen}
                onClose={sheet.close}
                toggle={sheet.toggle}
                snapPoint={0.5}
            >
                <div className="h-full">
                    <div className="flex flex-col h-full px-4 py-2 overflow-y-scroll gap-[10px]">
                        {records.data?.map((record) => {
                            return (
                                <button
                                    key={record.id}
                                    onClick={() => selectRecord(record)}
                                >
                                    <RecordCard />
                                </button>
                            );
                        })}
                        <RecordCard />
                        <RecordCard />
                        <RecordCard />
                        <RecordCard />
                    </div>
                </div>
            </BottomSheet>
        </div>
    );
};

const Title = ({ title }: { title: string }) => {
    return (
        <div className="text-sm font-bold text-black mb-[10px]">{title}</div>
    );
};

export default ProejctEdit;
