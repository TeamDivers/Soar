import React from 'react';

import { Home, Portfolio, Ranking, Record } from '../assets/images';

export const menus = [
    {
        title: '홈',
        route: '/',
        Icon: ({ color }: { color: string }) => <Home fill={color} />
    },
    {
        title: '학습기록',
        route: '/record',
        Icon: ({ color }: { color: string }) => <Record fill={color} />
    },
    {
        title: '포트폴리오',
        route: '/portfolio',
        Icon: ({ color }: { color: string }) => <Portfolio stroke={color} />
    },
    {
        title: '랭킹',
        route: '/ranking',
        Icon: ({ color }: { color: string }) => <Ranking stroke={color} />
    }
];
