import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <div>
            <h1 className="text-3xl font-bold underline">Home</h1>
            <div>
                <Link to={'/portfolio'}>portfolio</Link>
            </div>
            <div>
                <Link to={'/profile'}>profile</Link>
            </div>
            <div>
                <Link to={'/record'}>record</Link>
            </div>
            <div>
                <Link to={'/ranking'}>ranking</Link>
            </div>
            <div>
                <Link to={'/ranking/search'}>search</Link>
            </div>
            <div>
                <Link to={'/login'}>login</Link>
            </div>
        </div>
    );
};

export default Home;
