import React from 'react';
import { useNavigate } from 'react-router-dom';

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
    return (
        <Layout title="랭킹보드" right={SearchButton}>
            <div className=""></div>
        </Layout>
    );
};

export default Ranking;
