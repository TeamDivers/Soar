import moment from 'moment';
import React from 'react';
import { useNavigate } from 'react-router-dom';

import Dot from '@components/Dot';
import Layout from '@components/layout';
import RoundButton from '@components/RoundButton';

import { Left, Setting, Star } from '@images/index';

import { text2Color } from '@utils/color';

const PortfolioDetail = () => {
    const navigate = useNavigate();

    const color = '#423d75';

    const handleBookmark = () => {};

    return (
        <Layout hasHeader={false}>
            <div className="relative z-10 pt-12 pb-4">
                {/* Header */}
                <div className="flex items-start justify-between px-4">
                    <button onClick={() => navigate(-1)}>
                        <Left stroke="#ffffff" style={{ width: 8 }} />
                    </button>
                    <div className="flex flex-col items-center gap-1 text-xl font-bold text-white">
                        <h1>수학 과외용 포트폴리오</h1>
                        <div className="flex items-baseline gap-1 text-xs font-normal text-white">
                            {/* SB AggroOTF */}
                            <Star /> 4.5
                        </div>
                    </div>
                    <button>
                        <Setting style={{ marginTop: 6 }} />
                    </button>
                </div>
                {/* Profile */}
                <div className="flex flex-col items-center gap-[10px] justify-center mt-2">
                    <img
                        src="https://placehold.co/200"
                        className="w-[93px] h-[93px] rounded-[76px]"
                    />
                    <div className="text-base font-bold text-black">전진호</div>
                </div>
                {/* Projects */}
                <div className="px-4">
                    {/* Project */}
                    <div>
                        <h2
                            className="mb-4 text-base font-bold"
                            style={{ color }}
                        >
                            확률과 통계
                        </h2>
                        <div className="bg-white rounded-[10px] shadow-md px-4 pt-5 pb-4">
                            <div className="flex items-center justify-between gap-3 mb-5">
                                <div className="flex gap-3">
                                    <Dot color={text2Color('수학')} size={24} />
                                    <span className="text-base font-semibold text-black">
                                        수학
                                    </span>
                                </div>
                                <div>
                                    <span className="text-sm font-medium text-neutral-400">
                                        {moment(new Date()).format(
                                            'YYYY.MM.DD, H:MMa'
                                        )}
                                        -
                                        {moment(new Date()).format(
                                            'YYYY.MM.DD, H:MMa'
                                        )}
                                    </span>
                                </div>
                            </div>
                            <img
                                src="http://placehold.co/800x400"
                                className="rounded-[10px] border-[2px] mb-6"
                                style={{ borderColor: color }}
                            />
                            <div className="w-full bg-zinc-100 rounded-[10px] p-4 text-neutral-500 text-sm font-medium mb-4">
                                새로운 풀이과정을 도입한 확통 24,35,36번
                                풀이.짜-x, 짬-y, 탕-z 대입.
                            </div>
                            <div className="relative">
                                <button
                                    onClick={handleBookmark}
                                    className="flex justify-center items-center absolute w-[60px] h-[60px] bg-zinc-400 rounded-full shadow-md right-0 top-[-8px]"
                                >
                                    <Star
                                        fill="#ffffff"
                                        style={{ width: 30, height: 'auto' }}
                                    />
                                </button>
                                <RoundButton
                                    backgroundColor={color}
                                    onClick={function (): void {
                                        /** TODO: navigate to /record/:id */
                                    }}
                                >
                                    <div className="text-base font-semibold text-white py-[10px]">
                                        학습기록 보러가기
                                    </div>
                                </RoundButton>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div
                className="absolute -left-[25px] top-[-90px] w-[500px] h-[260px] rounded-[50%] z-0 "
                style={{ backgroundColor: color }}
            />
        </Layout>
    );
};

export default PortfolioDetail;
