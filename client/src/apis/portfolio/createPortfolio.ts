import { useMutation, useQuery } from 'react-query';

import { queryClient } from '@configs/reactQuery';

import { request } from '../axios';

export interface CreatePortfolioType {
    content: string;
    isPublic: boolean;
    category: string;
    startDate: string;
    endDate: string;
    tagName: string;
    memberId: number;
    files: File[];
}

const createPortfolio = (params: CreatePortfolioType) => {
    const formData = new FormData();
    const { files, ...recordInfo } = params;

    params.files.map((file) => {
        formData.append('files', file);
    });

    formData.append(
        'studyhistory',
        new Blob([JSON.stringify(recordInfo, null, 2)], {
            type: 'application/json'
        })
    );

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
            queryClient.invalidateQueries({ queryKey: ['records'] });
        }
    });
};
