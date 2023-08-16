import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { useCreateRecord } from '@apis/record/createRecord';
import { Title } from '@routes/portfolio/create';

import CreateNavigation from '@components/CreateNavigation';
import Divider from '@components/Divider';
import FileInput from '@components/FileInput';
import TextInput from '@components/TextInput';

import useTextInput from '@hooks/useTextInput';

const RecordCreate = () => {
    const navigate = useNavigate();

    const { mutate } = useCreateRecord();

    const { value: title, onChange: onChangeTitle } = useTextInput();
    const [file, setFile] = useState<File>();

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
            <div className="flex flex-col justify-between px-4 pt-[34px] mb-2 overflow-y-scroll h-screen pb-[100px]">
                {/* portfolio title section */}
                <div className="my-[40px]">
                    <Title text="포트폴리오 제목" />
                    <TextInput
                        value={title}
                        onChange={onChangeTitle}
                        placeholder="기억될만한 제목을 만들어보세요."
                    />
                </div>
                <Divider />
                {/* 썸네일 추가 */}
                <section>
                    <div className="my-[40px]">
                        <Title text="썸네일 추가" isRequired={false} />
                        <div className="flex flex-col gap-2 mb-2">
                            <FileInput
                                label={'이미지'}
                                onChange={handleOnChangeFileInput}
                            />
                        </div>
                    </div>
                </section>
                <Divider />
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
