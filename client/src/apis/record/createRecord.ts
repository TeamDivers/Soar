import { useMutation, useQuery } from 'react-query';

import { queryClient } from '@configs/reactQuery';

import { request } from '../axios';

import { RecordType } from './getRecords';

export interface CreateRecordType {
    content: string;
    isPublic: boolean;
    category: string;
    startDate: string;
    endDate: string;
    tagName: string;
    memberId: number;
}

const createRecord = (params: CreateRecordType) => {
    return request<RecordType>({
        method: 'POST',
        url: '/studyhistories',
        data: params
    });
};

export const useCreateRecord = () => {
    return useMutation({
        mutationFn: createRecord,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['records'] });
        }
    });
};
