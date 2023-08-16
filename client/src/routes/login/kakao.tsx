import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { useGetMemberId } from '@apis/member/getMember';

import { ACCESS_TOKEN_KEY, REFRESH_TOKEN_KEY } from '@constants/index';

import {
    setAccessToken,
    setCookie,
    setMemberId,
    setRefreshToken
} from '@utils/auth';

const Kakao = () => {
    const navigate = useNavigate();

    const memberId = useGetMemberId();
    const [token, setToken] = useState<string | undefined>();

    useEffect(() => {
        if (memberId.data) {
            setMemberId(memberId.data.id.toString());

            navigate('/');
        }
    }, [memberId.data]);

    useEffect(() => {
        if (token) {
            setAccessToken(token);

            memberId.refetch();
        }
    }, [token]);

    useEffect(() => {
        const params = new URL(document.location.toString()).searchParams;
        const token = params.get('token');

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
