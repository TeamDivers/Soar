import { useQuery } from 'react-query';

import { request } from '@apis/axios';

import { MemberType } from '@interfaces/member';

const getMember = (memberId: number) => {
    return request<MemberType>({
        method: 'GET',
        url: `/members/${memberId}`
    });
};

const getMemeberId = () => {
    return request<MemberType>({
        method: 'GET',
        url: `/members`
    });
};

export const useGetMember = ({ memberId }: { memberId: number }) => {
    return useQuery(['member', memberId], () => getMember(memberId));
};

export const useGetMemberId = () => {
    return useQuery(['member'], () => getMemeberId());
};
