import { useMutation } from 'react-query';

import { queryClient } from '@configs/reactQuery';

import { CreatePortfolioDto } from '@interfaces/portfolio';

import { request } from '../axios';

const createPortfolio = (params: CreatePortfolioDto) => {
    const formData: any = new FormData();

    formData.append(
        'portfolio',
        new Blob([JSON.stringify(params.portfolio, null, 2)], {
            type: 'application/json'
        })
    );

    formData.append(
        'projectList',
        new Blob(
            [
                JSON.stringify(
                    Array.from({ length: params.files.length }, () => 1),
                    null,
                    2
                )
            ],
            {
                type: 'application/json'
            }
        )
    );

    formData.append(
        'fileNumbers',
        new Blob([JSON.stringify(params.projectList, null, 2)], {
            type: 'application/json'
        })
    );

    params.files.map((file) => {
        formData.append('files', file);
    });

    formData.append('thumbnail', params.thumbnail);

    return request<Response>({
        method: 'POST',
        url: '/portfolios',
        data: formData,
        headers: {
            'Content-Type': 'multipart/form-data;'
        }
    });
};

export const useCreatePortfolio = () => {
    return useMutation({
        mutationFn: createPortfolio,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['portfolios'] });
        }
    });
};
