import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { useCreateRecord } from '@apis/record/createRecord';
import { Title } from '@routes/portfolio/create';

import CreateNavigation from '@components/CreateNavigation';
import DateTimePicker from '@components/DateTimePicker';
import Divider from '@components/Divider';
import FileInput from '@components/FileInput';
import TextArea from '@components/TextArea';
import TextInput from '@components/TextInput';

import useTextInput from '@hooks/useTextInput';

const RecordCreate = () => {
    const navigate = useNavigate();

    const { mutate } = useCreateRecord();

    const content = useTextInput();
    const category = useTextInput();
    const { value: title, onChange: onChangeTitle } = useTextInput();
    const [file, setFile] = useState<File>();
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');

    const handleOnSave = () => {
        if (file) {
            // mutate({});
        }
    };

    const handleOnChangeFileInput = (file: File) => {
        setFile(file);
    };

    return (
        <>
            <div className="flex flex-col justify-start px-4 pt-[34px] mb-2 overflow-y-scroll h-screen pb-[100px]">
                {/* portfolio title section */}
                <div className="mb-[40px]">
                    <Title text="제목" />
                    <TextInput
                        value={title}
                        onChange={onChangeTitle}
                        placeholder="기억될만한 제목을 만들어보세요."
                    />
                </div>
                <Divider />
                {/* 날짜 및 시간 추가 */}
                <div className="my-5">
                    <Title text={'날짜 및 시간'} />
                    <div className="flex justify-between gap-[6px] items-center">
                        <DateTimePicker
                            value={startDate}
                            onChange={setStartDate}
                        />
                        <span className="text-base font-medium text-neutral-500">
                            ~
                        </span>
                        <DateTimePicker value={endDate} onChange={setEndDate} />
                    </div>
                </div>
                <Divider />
                {/* 공부 내용 */}
                <section>
                    <div className="my-[40px]">
                        <Title text="공부 내용" isRequired={false} />
                        <TextArea
                            value={content.value}
                            placeholder="공부에 대한 내용을 입력해주세요."
                            onChange={content.onChange}
                        />
                    </div>
                </section>
                <Divider />
                {/* 카테고리 입력 */}
                <section>
                    <div className="my-[40px]">
                        <Title text="카테고리 입력" isRequired={false} />
                        <TextInput
                            value={category.value}
                            onChange={category.onChange}
                        />
                    </div>
                </section>
                <Divider />
                {/* 기타 파일 추가 */}
                <section>
                    <div className="my-[40px]">
                        <Title text="기타 파일 추가" isRequired={false} />
                        <div className="flex flex-col gap-2 mb-2">
                            <FileInput
                                label={'파일'}
                                onChange={handleOnChangeFileInput}
                            />
                        </div>
                    </div>
                </section>
            </div>
            <CreateNavigation
                onCancel={function (): void {
                    navigate(-1);
                }}
                onSave={handleOnSave}
            />
        </>
    );
};

export default RecordCreate;
