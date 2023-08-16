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

import useBottomSheet from '@hooks/useBottomSheet';
import useTextInput from '@hooks/useTextInput';

import { RecordType } from '@interfaces/record';

export interface ProjectType {
    title: string;
    startDate: string;
    endDate: string;
    content: string;
    recoredIds: number[];
    file: File | undefined;
}

interface ProejctEditProps {
    onAddProject: (proejct: ProjectType) => void;
}

const ProejctEdit = ({ onAddProject }: ProejctEditProps) => {
    const sheet = useBottomSheet();

    const { data: records } = useGetRecords({ memberId: 1 });

    const title = useTextInput();
    const content = useTextInput();
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [selectedRecords, setSelectedRecords] = useState<RecordType[]>([]);
    const [file, setFile] = useState<File>();

    const selectRecord = (newRecord: RecordType) => {
        setSelectedRecords((prev) => [...prev, newRecord]);
        sheet.close();
    };

    const handleOnClickAddRecord = () => {
        sheet.toggle();
    };

    const handleOnClickAddProject = () => {
        onAddProject({
            title: title.value,
            startDate,
            endDate,
            content: content.value,
            recoredIds: selectedRecords.map((record) => record.id),
            file
        });
    };

    const handleOnChangeFileInput = (file: File) => {
        setFile(file);
    };

    return (
        <div className="flex flex-col px-4 py-6 shadow-md rounded-[10px] bg-white">
            <div className="mb-5">
                <Title title={'공부명'} />
                <TextInput
                    size="sm"
                    value={title.value}
                    placeholder="공부명을 입력해주세요"
                    onChange={title.onChange}
                />
            </div>
            <div className="mb-5">
                <Title title={'날짜 및 시간'} />
                <div className="flex justify-between gap-[6px] items-center">
                    <DateTimePicker value={startDate} onChange={setStartDate} />
                    <span className="text-base font-medium text-neutral-500">
                        ~
                    </span>
                    <DateTimePicker value={endDate} onChange={setEndDate} />
                </div>
            </div>
            <div className="mb-5">
                <Title title={'설명'} />
                <TextArea
                    value={content.value}
                    placeholder="공부에 대한 내용을 입력해주세요."
                    onChange={content.onChange}
                />
            </div>
            <div className="mb-5">
                <Title title={'학습기록 가져오기'} />
                <div className="flex flex-col gap-2 mb-2">
                    {selectedRecords.map((selectedRecord) => {
                        return (
                            <RecordCard
                                key={selectedRecord.id}
                                record={selectedRecord}
                            />
                        );
                    })}
                </div>
                <PlusButton onClick={handleOnClickAddRecord} />
            </div>
            <div className="mb-5">
                <Title title={'공부 기록'} />
                <div className="flex flex-col gap-2 mb-2">
                    <FileInput
                        label={'파일'}
                        onChange={handleOnChangeFileInput}
                    />
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
                        {records?.map((record) => {
                            return (
                                <button
                                    key={record.id}
                                    onClick={() => selectRecord(record)}
                                >
                                    <RecordCard record={record} />
                                </button>
                            );
                        })}
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
