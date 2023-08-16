import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { ACCESS_TOKEN_KEY, REFRESH_TOKEN_KEY } from '@constants/index';

import { setAccessToken, setCookie, setRefreshToken } from '@utils/auth';

const Kakao = () => {
    const navigate = useNavigate();

    const [token, setToken] = useState<string | undefined>();

    useEffect(() => {
        if (token) {
            setAccessToken(token);
            navigate('/');
        }
    }, [token]);

    useEffect(() => {
        const params = new URL(document.location.toString()).searchParams;
        const token = params.get('token');
        console.log('🚀 ~ file: kakao.tsx:15 ~ useEffect ~ token:', token);

        /** in case of user has problem with kakao auth server */
        if (token === null) {
            navigate('/login');
            return;
        }

        try {
            /** send code to server and retrieve tokens */
            setToken(token);
        } catch (err) {
            console.error(err);
            navigate('/login');
        }
    }, []);

    return <>{/* TODO: BoundLoader will be added */}</>;
};

export default Kakao;
