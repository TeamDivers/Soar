import { useQuery } from 'react-query';

import { OPTION } from '@interfaces/option';
import { RecordType } from '@interfaces/record';

import { request } from '../axios';

interface RecordRequestParams {
    memberId: number;
    option?: OPTION;
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

const getRecordCalendar = ({
    memberId,
    year,
    month
}: {
    memberId: number;
    year: number;
    month: number;
}) => {
    return request<any>({
        method: 'GET',
        url: `/studyhistories/calendar/${memberId}`,
        params: { year, month }
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

export const useGetRecordCalendar = ({
    memberId,
    year,
    month
}: {
    memberId: number;
    year: number;
    month: number;
}) => {
    return useQuery(['record', memberId, year, month], () =>
        getRecordCalendar({ memberId, year, month })
    );
};
