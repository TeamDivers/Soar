import { useQuery } from 'react-query';

import { OPTION } from '@interfaces/option';
import { PortfolioType } from '@interfaces/portfolio';

import { request } from '../axios';

interface PortfolioRequestParams {
    memberId: number;
    option?: OPTION;
}

const getPortfolio = (portfolioId: number) => {
    return request<PortfolioType>({
        method: 'GET',
        url: `/portfolios/${portfolioId}`
    });
};

const getPortfolios = ({
    memberId,
    option = 'newest'
}: PortfolioRequestParams) => {
    return request<PortfolioType[]>({
        method: 'GET',
        url: '/portfolios',
        params: { memberId, option }
    });
};

const getPortfolioRank = (portfolioId: number) => {
    return request<number>({
        method: 'GET',
        url: `/portfolios/rank/${portfolioId}`
    });
};

export const useGetPortfolios = ({
    memberId,
    option = 'newest'
}: PortfolioRequestParams) => {
    return useQuery(['portfolios'], () => getPortfolios({ memberId, option }));
};

export const useGetPortfolio = ({ portfolioId }: { portfolioId: number }) => {
    return useQuery(['portfolio', portfolioId], () =>
        getPortfolio(portfolioId)
    );
};

export const usePortfolioRank = ({ portfolioId }: { portfolioId: number }) => {
    return useQuery(['portfolio', 'rank', portfolioId], () =>
        getPortfolioRank(portfolioId)
    );
};
