import axios from 'axios';
import React from 'react';

import RoundButton from '@components/RoundButton';

import { Google, Kakao, Logo } from '@images/index';

const Login = () => {
    const handleKakaoLogin = () => {
        axios.get('http://117.16.137.205:8080/oauth2/authorization/kakao');
    };

    return (
        <div className="flex items-center justify-center min-h-screen bg-white">
            <div className="flex flex-col items-center justify-center">
                <h1 className="mb-[10px] text-sm font-normal text-slate-500">
                    MZ다운 나만의 공부
                </h1>
                <Logo />
            </div>
            <div className="fixed max-w-md mx-auto bottom-[80px] w-full px-4 flex flex-col gap-[10px]">
                <RoundButton
                    backgroundColor="#FEE500"
                    onClick={handleKakaoLogin}
                >
                    <div className="relative flex justify-center w-full">
                        <Kakao className="absolute left-[18px]" />
                        <span className="text-[#181600] text-lg font-medium">
                            카카오톡으로 로그인
                        </span>
                    </div>
                </RoundButton>
                <RoundButton
                    backgroundColor="white"
                    hasBorder
                    onClick={() => {
                        location.href =
                            'http://117.16.137.205:8080/oauth2/authorization/google';
                    }}
                >
                    <div className="relative flex justify-center w-full">
                        <Google className="absolute left-[18px]" />
                        <span className="text-[#242832] text-lg font-medium ">
                            구글계정으로 로그인
                        </span>
                    </div>
                </RoundButton>
            </div>
        </div>
    );
};

export default Login;
