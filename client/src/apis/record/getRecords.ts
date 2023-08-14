import { useQuery } from 'react-query';

import { request } from '../axios';

export interface RecordType {
    id: number;
    type: string;
    content: string;
    memberId: number;
    category: string;
    startDate: string;
    endDate: string;
}

const getRecords = () => {
    return request<RecordType[]>({
        method: 'GET',
        url: '/studyhistories'
    });
};

const useGetRecords = () => {
    return useQuery(['records'], () => getRecords());
};

export default useGetRecords;
