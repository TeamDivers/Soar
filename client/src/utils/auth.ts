import { ACCESS_TOKEN_KEY, REFRESH_TOKEN_KEY } from '@constants/index';

let inMemoryAccessToken: string | undefined;

export const setMemberId = (memberId: string) => {
    localStorage.setItem('MEMBER_ID', memberId);
};

export const getMemberId = (): string | null => {
    return localStorage.getItem('MEMBER_ID');
};

export const setAccessToken = (accessToken: string) => {
    localStorage.setItem(ACCESS_TOKEN_KEY, accessToken);
};

export const getAccessToken = (): string | null => {
    return localStorage.getItem(ACCESS_TOKEN_KEY);
};

export const deleteAccessToken = () => {
    inMemoryAccessToken = '';
};

export const getRefreshToken = () => {
    return getCookie(REFRESH_TOKEN_KEY);
};

export const setRefreshToken = (refreshToken: string) => {
    setCookie(REFRESH_TOKEN_KEY, refreshToken);
};

export const setCookie = (name: string, value: string, days: number = 14) => {
    const expires = new Date();
    expires.setTime(expires.getTime() + days * 24 * 60 * 60 * 1000);
    const cookieValue =
        encodeURIComponent(value) +
        (days ? `; expires=${expires.toUTCString()}` : '');
    document.cookie = `${name}=${cookieValue}; path=/; HttpOnly; Secure; SameSite=None`;
};

export const getCookie = (name: string): string | null => {
    const cookieArr = document.cookie.split(';');
    for (const cookie of cookieArr) {
        const [cookieName, cookieValue] = cookie.trim().split('=');
        if (cookieName === name) {
            return decodeURIComponent(cookieValue);
        }
    }
    return null;
};
