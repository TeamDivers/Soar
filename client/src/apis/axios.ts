/* eslint-disable import/named */
import axios, { AxiosRequestConfig } from 'axios';

export interface Response<T> {
    success: boolean;
    status: number;
    message: string;
    result: T;
}

const instance = axios.create({
    baseURL: `${process.env.API_BASE_URI}`,
    timeout: 3000
});

instance.interceptors.request.use((config) => {
    return config;
});

export const request = <T>(config: AxiosRequestConfig): Promise<T> => {
    return instance
        .request<Response<T>>(config)
        .then((response) => response.data.result);
};

export default instance;
