import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <div>
            <h1 className="text-3xl font-bold underline">Home</h1>
            <Link to={'/portfolio'}>portfolio</Link>
            <Link to={'/login'}>login</Link>
        </div>
    );
};

export default Home;
