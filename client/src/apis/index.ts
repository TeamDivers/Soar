import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://117.16.137.205:8080',
    timeout: 3000
});

instance.interceptors.request.use((config) => {
    return config;
});

export default instance;
