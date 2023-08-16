import { useMutation } from 'react-query';

import { queryClient } from '@configs/reactQuery';

import { request } from '@apis/axios';

interface Variable {
    portfolioId: number;
    memberId: number;
    expertiseScore: number;
    differenceScore: number;
    perfectionScore: number;
}

const ratePortfolio = (props: Variable) => {
    const data = props;

    return request<any>({
        method: 'POST',
        url: '/portfolios/rate',
        data,
        headers: {
            'Content-Type': 'application/json;'
        }
    });
};

export const useRatePortfolio = () => {
    return useMutation({
        mutationFn: ratePortfolio,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['portfolios'] });
        }
    });
};
