import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import ChipList from '@components/ChipList';
import Layout from '@components/layout';
import Modal from '@components/Modal';
import PortfolioCard from '@components/PortfolioCard';
import Ring from '@components/Ring';
import RoundButton from '@components/RoundButton';

import { Search } from '@images/index';

const SearchButton = () => {
    const navigate = useNavigate();

    return (
        <button onClick={() => navigate('/ranking/search')}>
            <Search />
        </button>
    );
};

const Ranking = () => {
    const categories = [
        '기본',
        '디자인',
        '수학',
        '개발',
        '영어',
        '국어',
        '코딩',
        '캘리그라피'
    ];

    /** TOOD: filter portfolios with category */
    const [category, setCategory] = useState(categories[0]);
    const [isOpen, setIsOpen] = useState(false);

    const closeModal = () => setIsOpen(false);

    const onClickChip = (label: string) => {
        setCategory(label);
    };

    const onClickMyRanking = () => {
        /** TODO: open modal and shows my portfolios */
        setIsOpen(true);
    };

    return (
        <Layout title="랭킹보드" right={SearchButton}>
            <div className="mt-4">
                <ChipList chips={categories} onClick={onClickChip} />
            </div>
            <div className="flex items-end justify-center gap-6 mt-10">
                <div className="flex flex-col items-center gap-5">
                    <Ring
                        size="md"
                        rank={2}
                        color="#1D5CFF"
                        img="https://placehold.co/200"
                    />
                    <PortfolioInfo
                        title={'UX 프리랜서 지원'}
                        desc={'김민지 / 학생'}
                    />
                </div>
                <div className="flex flex-col items-center gap-5">
                    <Ring
                        size="lg"
                        rank={1}
                        color="#FFC01D"
                        img="https://placehold.co/200"
                    />
                    <PortfolioInfo
                        title={'UX 프리랜서 지원'}
                        desc={'김민지 / 학생'}
                    />
                </div>
                <div className="flex flex-col items-center gap-5">
                    <Ring
                        size="md"
                        rank={3}
                        color="#1D5CFF"
                        img="https://placehold.co/200"
                    />
                    <PortfolioInfo
                        title={'UX 프리랜서 지원'}
                        desc={'김민지 / 학생'}
                    />
                </div>
            </div>
            <div className="mt-[30px] px-4">
                <RoundButton onClick={onClickMyRanking}>
                    <div className="py-[9px] text-white text-lg font-semibold">
                        나의 랭킹
                    </div>
                </RoundButton>
            </div>
            <Modal isOpen={isOpen} close={closeModal}>
                <div className="w-screen max-w-md px-5">
                    <div className="flex flex-col pt-10 bg-white rounded-[20px] shadow h-[500px]">
                        <div className="flex items-start justify-between px-5 mb-2">
                            <div className="flex gap-[10px] items-center">
                                <img
                                    src="https://placehold.co/200"
                                    className="w-10 h-10 rounded-full"
                                />
                                <div className="flex flex-col">
                                    <span className="text-base font-normal text-black mb-[2px]">
                                        {/* SB AggroOTF */}
                                        전진호님의 랭킹
                                    </span>
                                    <span className="text-sm font-normal text-neutral-400">
                                        총 4개의 포트폴리오
                                    </span>
                                </div>
                            </div>
                            <button
                                className=""
                                onClick={closeModal}
                                type={'button'}
                            >
                                X
                            </button>
                        </div>
                        <div className="flex flex-col gap-2 px-5 py-2 overflow-x-visible overflow-y-scroll">
                            <PortfolioCard
                                size="sm"
                                ranking={24}
                                rating={3.5}
                            />
                            <PortfolioCard
                                size="sm"
                                ranking={58}
                                rating={3.0}
                            />
                            <PortfolioCard
                                size="sm"
                                ranking={58}
                                rating={3.0}
                            />
                            <PortfolioCard
                                size="sm"
                                ranking={58}
                                rating={3.0}
                            />
                        </div>
                    </div>
                </div>
            </Modal>
        </Layout>
    );
};

export default Ranking;

const PortfolioInfo = ({ title, desc }: { title: string; desc: string }) => {
    return (
        <div className="flex flex-col items-center">
            <span className="text-sm font-bold text-black">{title}</span>
            <span className="text-xs font-normal text-neutral-400">{desc}</span>
        </div>
    );
};
