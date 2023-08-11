import React from 'react';

import RoundButton from '@components/RoundButton';

import { Google, Kakao, Logo } from '@images/index';

const Login = () => {
    return (
        <div className="flex items-center justify-center min-h-screen bg-white">
            <div className="flex flex-col items-center justify-center">
                <h1 className="mb-[10px] text-sm font-normal text-slate-500">
                    MZ다운 나만의 공부
                </h1>
                <Logo />
            </div>
            <div className="fixed bottom-[80px] w-full px-4 flex flex-col gap-[10px]">
                <RoundButton
                    backgroundColor="#FEE500"
                    textColor="#181600"
                    onClick={function (): void {
                        throw new Error('Function not implemented.');
                    }}
                >
                    <Kakao />
                    카카오톡으로 로그인
                </RoundButton>
                <RoundButton
                    backgroundColor="white"
                    textColor="#242832"
                    hasBorder
                    onClick={function (): void {
                        throw new Error('Function not implemented.');
                    }}
                >
                    <Google />
                    구글계정으로 로그인
                </RoundButton>
            </div>
        </div>
    );
};

export default Login;
