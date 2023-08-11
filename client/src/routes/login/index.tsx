import React from 'react';

import RoundButton from '@components/RoundButton';

import { Logo } from '@images/index';

const Login = () => {
    return (
        <div className="flex items-center justify-center min-h-screen bg-white">
            <div className="flex flex-col items-center justify-center">
                <h1 className="mb-[10px] text-sm font-normal text-slate-500">
                    MZ다운 나만의 공부
                </h1>
                <Logo />
            </div>
            <div className="fixed bottom-[80px] w-full px-4">
                <RoundButton>카카오톡으로 로그인</RoundButton>
            </div>
        </div>
    );
};

export default Login;
