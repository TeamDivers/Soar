import React from 'react';

import { Home, Portfolio, Ranking, Record } from '../assets/images';

export const menus = [
    {
        title: '홈',
        route: '/',
        icon: (color: string) => <Home className="fill-primary" />
    },
    {
        title: '학습기록',
        route: '/record',
        icon: (color: string) => <Record className="fill-primary" />
    },
    {
        title: '포트폴리오',
        route: '/portfolio',
        icon: (color: string) => <Portfolio className=" stroke-primary" />
    },
    {
        title: '랭킹',
        route: '/ranking',
        icon: (color: string) => <Ranking className="stroke-primary" />
    }
];
