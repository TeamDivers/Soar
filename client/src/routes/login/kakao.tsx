import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Kakao = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const params = new URL(document.location.toString()).searchParams;
        const code = params.get('code');

        /** send code to server and retrieve tokens */

        navigate('/');
    }, []);

    return <>kakao loading</>;
};

export default Kakao;
