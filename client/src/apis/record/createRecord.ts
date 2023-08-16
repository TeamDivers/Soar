import { useMutation, useQuery } from 'react-query';

import { queryClient } from '@configs/reactQuery';

import { request } from '../axios';

import { RecordType } from './getRecords';

queryClient.invalidateQueries({ queryKey: ['todos'] });

const createRecord = () => {
    return request<RecordType[]>({
        method: 'POST',
        url: '/studyhistories'
    });
};

export const useCreateRecord = () => {
    return useMutation({
        mutationFn: () => createRecord()
    });
};
