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
    files: File[];
}

const createRecord = (params: CreateRecordType) => {
    const formData = new FormData();
    const { files, ...recordInfo } = params;

    // timelaps

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
        url: '/studyhistories',
        data: formData,
        headers: {
            'Content-Type': 'multipart/form-data;'
        }
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
