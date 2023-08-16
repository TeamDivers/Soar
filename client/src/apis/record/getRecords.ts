import { useQuery } from 'react-query';

import { OPTION } from '@interfaces/option';
import { RecordType } from '@interfaces/record';

import { request } from '../axios';

interface RecordRequestParams {
    memberId: number;
    option: OPTION;
}

const getRecord = (historyId: number) => {
    return request<RecordType>({
        method: 'GET',
        url: `/studyhistories/${historyId}`
    });
};

const getRecords = ({ memberId, option }: RecordRequestParams) => {
    return request<RecordType[]>({
        method: 'GET',
        url: '/studyhistories',
        params: { memberId, option }
    });
};

export const useGetRecords = ({
    memberId,
    option = 'newest'
}: RecordRequestParams) => {
    return useQuery(['records'], () => getRecords({ memberId, option }));
};

export const useGetRecord = ({ historyId }: { historyId: number }) => {
    return useQuery(['record', historyId], () => getRecord(historyId));
};
