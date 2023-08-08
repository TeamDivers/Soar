let inMemoryAccessToken: string | undefined;

export const setAccessToken = (accessToken: string) => {
    inMemoryAccessToken = accessToken;
};

export const getAccessToken = (): string | undefined => {
    return inMemoryAccessToken;
};

export const deleteAccessToken = () => {
    inMemoryAccessToken = '';
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
