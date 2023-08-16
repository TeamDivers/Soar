import React from 'react';

import Divider from '@components/Divider';
import TextInput from '@components/TextInput';

import useTextInput from '@hooks/useTextInput';

const PortfolioCreate = () => {
    const { value: title, onChange: onChangeTitle } = useTextInput();

    return (
        <div className="flex flex-col justify-between px-4 pt-[34px] mb-2">
            <div className="flex items-center gap-[22px] mb-[30px]">
                <img
                    src="https://placehold.co/400/000000/FFF"
                    className="w-[82px] h-[82px] rounded-full"
                />
                <div className="flex flex-col gap-[6px] ">
                    <span className="text-lg font-normal text-black">
                        전진호
                    </span>
                    <span className="text-sm font-normal text-neutral-400">
                        대학생
                    </span>
                </div>
            </div>
            <div className="flex flex-col gap-[14px] mb-[30px]">
                <Desc
                    label={'학력'}
                    value={'대학생 / 건국대학교 컴퓨터공학과'}
                    isRequired
                />
                <Desc label={'휴대폰'} value={'010-2370-9940'} isRequired />
                <Desc label={'이메일'} value={'jinho9940@gmail.com'} />
                <Desc label={'경력'} value={'wit 2기 졸업 외 6개'} />
            </div>
            <button
                className="rounded-[10px] border border-stone-300 py-3 text-black text-sm font-semibold mb-[40px]"
                onClick={function (): void {
                    throw new Error('Function not implemented.');
                }}
            >
                정보 수정
            </button>
            <Divider />
            <div className="my-[40px]">
                <Title text="포트폴리오 제목" />
                <TextInput
                    value={title}
                    onChange={onChangeTitle}
                    placeholder="기억될만한 제목을 만들어보세요."
                />
            </div>
            <Divider />
        </div>
    );
};

export default PortfolioCreate;

const Title = ({
    text,
    isRequired = true
}: {
    text: string;
    isRequired?: boolean;
}) => {
    return (
        <div className="flex gap-1 pb-4">
            <h2 className="text-xl font-bold text-black">{text}</h2>
            {isRequired && (
                <span className="text-xl font-bold text-red-600">*</span>
            )}
        </div>
    );
};

const Desc = ({
    label,
    value,
    isRequired = false
}: {
    label: string;
    value: string;
    isRequired?: boolean;
}) => {
    return (
        <div className="flex">
            <span className="text-sm font-normal text-neutral-500 w-[75px]">
                {label}{' '}
                {isRequired && (
                    <span className="text-sm font-normal text-red-600">*</span>
                )}
            </span>
            <span className="text-sm font-normal text-black">{value}</span>
        </div>
    );
};
