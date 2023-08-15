import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import ChipList from '@components/ChipList';
import Layout from '@components/layout';

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

    const [category, setCategory] = useState(categories[0]);

    const onClickChip = (label: string) => {
        setCategory(label);
    };

    return (
        <Layout title="랭킹보드" right={SearchButton}>
            <div className="mt-4">
                <ChipList chips={categories} onClick={onClickChip} />
            </div>
        </Layout>
    );
};

export default Ranking;
