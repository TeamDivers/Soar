import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import Indicator from '@components/Indicator';
import RadioInput from '@components/RadioInput';
import RoundButton from '@components/RoundButton';
import TextInput from '@components/TextInput';

import { Left } from '@images/index';

import useRadioInput from '@hooks/useRadioInput';
import useTextInput from '@hooks/useTextInput';

const TOTAL_STEP = 2;
const CTA = ['닉네임을 설정해주세요', '현재 직업을 선택해주세요'];

const SignUp = () => {
    const [step, setStep] = useState(1);
    const navigate = useNavigate();

    const handleOnClickButton = () => {
        if (step === 2) {
            navigate('/login/onboard');
        }

        setStep((prev) => prev + 1);
    };

    const handleOnClickBack = () => {
        setStep((prev) => prev - 1);
    };

    const contentRenderer = () => {
        if (step === 1) {
            return <Nickname />;
        }

        if (step === 2) {
            return <Job />;
        }

        return <></>;
    };

    return (
        <div className="flex flex-col">
            <div className="pt-[20px] pb-[16px] px-6">
                <button
                    onClick={handleOnClickBack}
                    className={`${step < 2 ? 'invisible' : ''}`}
                >
                    <Left />
                </button>
            </div>
            <Indicator progress={(step / TOTAL_STEP) * 100} />
            <div className="flex items-center justify-center w-10 h-10 bg-blue-100 rounded-full mt-[58px] mx-auto mb-[30px]">
                <div className="text-base font-normal text-center text-blue-600">
                    {step}
                </div>
            </div>
            <h1 className="text-black text-[22px] font-normal mx-auto mb-[30px]">
                {CTA[step - 1]}
            </h1>
            {contentRenderer()}
            <div className="fixed max-w-md mx-auto bottom-[40px] w-full px-4">
                <RoundButton onClick={handleOnClickButton}>
                    <div className="py-[13px] text-white text-xl font-bold leading-[24px]">
                        다음
                    </div>
                </RoundButton>
            </div>
        </div>
    );
};

const Nickname = () => {
    const { value: nickname, onChange: onChangeNickname } = useTextInput();

    return (
        <div className="px-4">
            <TextInput
                value={nickname}
                onChange={onChangeNickname}
                placeholder="이름 또는 닉네임"
            />
        </div>
    );
};

const Job = () => {
    const radioOptions = [
        { label: '고등학생', value: 'option1' },
        { label: '대학생', value: 'option2' },
        { label: '직장인', value: 'option3' },
        { label: '취준생', value: 'option4' },
        { label: '기타', value: 'option5' }
    ];

    const { selectedValue, handleOptionChange } = useRadioInput({
        initialSelectedValue: 'option1',
        options: radioOptions
    });

    return (
        <div className="px-[30px]">
            <RadioInput
                options={radioOptions}
                selectedValue={selectedValue}
                onChange={handleOptionChange}
            />
        </div>
    );
};

export default SignUp;
