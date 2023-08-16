import { useQuery } from 'react-query';

import { request } from '@apis/axios';

import { MemberType } from '@interfaces/member';

const getMember = (memberId: number) => {
    return request<MemberType>({
        method: 'GET',
        url: `/members/${memberId}`
    });
};

export const useGetMember = ({ memberId }: { memberId: number }) => {
    return useQuery(['member', memberId], () => getMember(memberId));
};
