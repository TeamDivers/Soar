import axios from 'axios';
import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import { ACCESS_TOKEN_KEY, REFRESH_TOKEN_KEY } from '@constants/index';

import { setAccessToken, setCookie, setRefreshToken } from '@utils/auth';

const Kakao = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const params = new URL(document.location.toString()).searchParams;
        const token = params.get('token');

        /** in case of user has problem with kakao auth server */
        if (!token) {
            navigate('/login');
        }

        try {
            /** send code to server and retrieve tokens */
            setAccessToken(token || '');
            navigate('/');
        } catch (err) {
            console.error(err);
            navigate('/login');
        }
    }, []);

    return <>{/* TODO: BoundLoader will be added */}</>;
};

export default Kakao;
