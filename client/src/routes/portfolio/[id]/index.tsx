import { Rating } from '@mui/material';
import moment from 'moment';
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

import { useGetMember } from '@apis/member/getMember';
import { useGetPortfolio } from '@apis/portfolio/getPortfolio';
import { useRatePortfolio } from '@apis/portfolio/ratePortfolio';

import BottomSheet from '@components/BottomSheet';
import Divider from '@components/Divider';
import Dot from '@components/Dot';
import Layout from '@components/layout';
import RoundButton from '@components/RoundButton';

import { Left, Setting, Star } from '@images/index';

import { getMemberId } from '@utils/auth';
import { text2Color } from '@utils/color';

import useBottomSheet from '@hooks/useBottomSheet';

const PortfolioDetail = () => {
    const params = useParams();
    const navigate = useNavigate();
    const bottomSheet = useBottomSheet();

    const [r1, setR1] = useState(0);
    const [r2, setR2] = useState(0);
    const [r3, setR3] = useState(0);
    const [r4, setR4] = useState(0);
    const hasRated = r1 > 0 && r2 > 0 && r3 > 0 && r4 > 0;

    const { data: portfolio } = useGetPortfolio({
        portfolioId: parseInt(params.portfolioId || '0')
    });

    const { data: user } = useGetMember({
        memberId: portfolio?.memberId || 0
    });

    const rateMutation = useRatePortfolio();

    const color = portfolio?.background;

    const handleBookmark = () => {
        bottomSheet.toggle();
    };

    const handleOnCloseSheet = () => {
        bottomSheet.close();
    };

    useEffect(() => {
        if (hasRated) {
            rateMutation.mutate({
                portfolioId: parseInt(params.portfolioId || '0'),
                memberId: parseInt(getMemberId()),
                expertiseScore: r1,
                differenceScore: r2,
                perfectionScore: r3
            });
        }
    }, [hasRated]);

    return (
        <Layout hasHeader={false}>
            <div className="relative z-10 pt-12 pb-4">
                {/* Header */}
                <div className="flex items-start justify-between px-4">
                    <button onClick={() => navigate(-1)}>
                        <Left stroke="#ffffff" style={{ width: 8 }} />
                    </button>
                    <div className="flex flex-col items-center gap-1 text-xl font-bold text-white">
                        <h1>{portfolio?.title}</h1>
                        <div className="flex items-baseline gap-1 text-xs font-normal text-white aggro">
                            {/* SB AggroOTF */}
                            <Star fill="#FFA800" /> 4.5
                        </div>
                    </div>
                    <button
                        onClick={handleBookmark}
                        className="flex items-center justify-center pt-1"
                    >
                        <Star
                            fill={hasRated ? '#FFA800' : '#ffffff'}
                            style={{
                                width: 18,
                                height: 'auto'
                            }}
                        />
                    </button>
                </div>
                {/* Profile */}
                <div className="flex flex-col items-center gap-[10px] justify-center mt-2">
                    <img
                        src={portfolio?.thumbnailURL}
                        className="w-[93px] h-[93px] rounded-[76px]"
                    />
                    <div className="text-base font-bold text-black">
                        {user?.name}
                    </div>
                </div>
                {/* Projects */}
                <div className="px-4">
                    {/* Project */}
                    {portfolio?.projects.map((project) => {
                        return (
                            <div key={project.projectId} className="mb-5">
                                <h2
                                    className="mb-4 text-base font-bold"
                                    style={{ color }}
                                >
                                    {project.title}
                                </h2>
                                <div className="bg-white rounded-[10px] shadow-md px-4 pt-5 pb-4">
                                    <div className="flex items-center justify-between gap-3 mb-5">
                                        <div className="flex gap-3">
                                            <Dot
                                                color={text2Color(
                                                    project.category
                                                )}
                                                size={24}
                                            />
                                            <span className="text-base font-semibold text-black">
                                                {project.category}
                                            </span>
                                        </div>
                                        <div>
                                            <span className="text-sm font-medium text-neutral-400">
                                                {moment(
                                                    project.startDate
                                                ).format('YYYY.MM.DD, H:MMa')}
                                                -
                                                {moment(project.endDate).format(
                                                    'YYYY.MM.DD, H:MMa'
                                                )}
                                            </span>
                                        </div>
                                    </div>
                                    {/* <img
                                        src={project.files[0].type}
                                        className="rounded-[10px] border-[2px] mb-6"
                                        style={{ borderColor: color }}
                                    /> */}
                                    <div className="w-full bg-zinc-100 rounded-[10px] p-4 text-neutral-500 text-sm font-medium mb-4">
                                        {project.description}
                                    </div>
                                    <div className="relative">
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
                        );
                    })}
                </div>
            </div>
            <div
                className="absolute -left-[25px] top-[-90px] w-[500px] h-[260px] rounded-[50%] z-0 "
                style={{ backgroundColor: color }}
            />
            <BottomSheet
                isOpen={bottomSheet.isOpen}
                onClose={handleOnCloseSheet}
                toggle={bottomSheet.toggle}
                snapPoint={0.5}
            >
                <div className="flex flex-col p-4 text-base font-semibold text-black">
                    <div className="flex justify-between pb-[30px]">
                        <div>전문성</div>
                        <Rating
                            precision={0.5}
                            onChange={(event, newValue) => {
                                setR1(newValue || 0);
                            }}
                        />
                    </div>
                    <Divider />
                    <div className="flex justify-between py-[30px]">
                        <div>차별성</div>
                        <Rating
                            precision={0.5}
                            onChange={(event, newValue) => {
                                setR2(newValue || 0);
                            }}
                        />
                    </div>
                    <Divider />
                    <div className="flex justify-between py-[30px]">
                        <div>완성도</div>
                        <Rating
                            precision={0.5}
                            onChange={(event, newValue) => {
                                setR3(newValue || 0);
                            }}
                        />
                    </div>
                    <Divider />
                    <div className="flex justify-between py-[30px]">
                        <div>총점</div>
                        <Rating
                            precision={0.5}
                            onChange={(event, newValue) => {
                                setR4(newValue || 0);
                            }}
                        />
                    </div>
                </div>
            </BottomSheet>
        </Layout>
    );
};

export default PortfolioDetail;
