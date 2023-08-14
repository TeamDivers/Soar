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

const getRecord = (historyId: number) => {
    return request<RecordType>({
        method: 'GET',
        url: `/studyhistories/${historyId}`
    });
};

const getRecords = () => {
    return request<RecordType[]>({
        method: 'GET',
        url: '/studyhistories'
    });
};

export const useGetRecords = () => {
    return useQuery(['records'], () => getRecords());
};

export const useGetRecord = ({ historyId }: { historyId: number }) => {
    return useQuery(['record'], () => getRecord(historyId));
};
