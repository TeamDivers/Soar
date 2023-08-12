import axios from 'axios';

import { REFRESH_TOKEN_KEY } from 'src/constants';
import { getRefreshToken, setRefreshToken } from 'src/utils/auth';

const refreshAccessToken = async () => {
    const refreshToken = getRefreshToken();

    const response = await axios({
        baseURL: 'TBD',
        url: '/token/refresh',
        method: 'get',
        headers: {
            Authorization: `Bearer ${refreshToken}`
        },
        validateStatus: null
    });

    setRefreshToken(response.headers[REFRESH_TOKEN_KEY]);
};

export default refreshAccessToken;
