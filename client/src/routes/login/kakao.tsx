import axios from 'axios';
import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import { ACCESS_TOKEN_KEY, REFRESH_TOKEN_KEY } from '@constants/index';

import { setAccessToken, setCookie, setRefreshToken } from '@utils/auth';

const Kakao = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const params = new URL(document.location.toString()).searchParams;
        const code = params.get('code');
        console.log('🚀 ~ file: kakao.tsx:15 ~ useEffect ~ code:', code);

        /** in case of user has problem with kakao auth server */
        if (!code) {
            navigate('/login');
        }

        try {
            /** send code to server and retrieve tokens */
            // axios
            //     .get(
            //         `${process.env.API_BASE_URI}/login/oauth2/code/kakao?code=${code}`
            //     )
            //     .then((response) => {
            //         const {
            //             [ACCESS_TOKEN_KEY]: accessToken,
            //             [REFRESH_TOKEN_KEY]: refreshToken
            //         } = response.headers;
            //         setAccessToken(accessToken);
            //         setRefreshToken(refreshToken);
            //         if (refreshToken) {
            //             setCookie(REFRESH_TOKEN_KEY, refreshToken);
            //             // `${REFRESH_TOKEN_KEY}=${refreshToken}; path=/; samesite=lax; httponly;`;
            //         }
            //
            //     });
            navigate('/');
        } catch (err) {
            console.error(err);
            navigate('/login');
        }
    }, []);

    return <>{/* TODO: BoundLoader will be added */}</>;
};

export default Kakao;
