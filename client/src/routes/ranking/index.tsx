import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import ChipList from '@components/ChipList';
import Layout from '@components/layout';
import Ring from '@components/Ring';

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

    const onClickChip = (label: string) => {
        setCategory(label);
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
