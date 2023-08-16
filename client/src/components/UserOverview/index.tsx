import React from 'react';

import { MemberType } from '@interfaces/member';

const UserOverview = (data: MemberType) => {
    return (
        <div className="flex-col w-[361px] h-[122px] rounded-[10px] border border-neutral-300 mt-[20px] py-[12px] px-[20px]">
            <div className="flex my-1">
                <div className="text-neutral-500 text-sm font-normal w-[75px]">
                    학력
                </div>
                <div className="text-black text-sm font-normal">
                    {data.education}
                    {/*대학생 / 건국대학교 컴퓨터공학과*/}
                </div>
            </div>
            <div className="flex my-1">
                <div className="text-neutral-500 text-sm font-normal w-[75px]">
                    휴대폰
                </div>
                <div className="text-black text-sm font-normal">
                    {data.phoneNumber}
                </div>
            </div>
            <div className="flex my-1">
                <div className="text-neutral-500 text-sm font-normal w-[75px]">
                    이메일
                </div>
                <div className="text-black text-sm font-normal">
                    {data.email}
                </div>
            </div>
            <div className="flex my-1">
                <div className="text-neutral-500 text-sm font-normal w-[75px]">
                    경력
                </div>
                <div className="text-black text-sm font-normal">
                    {data.career}
                </div>
            </div>
        </div>
    );
};

export default UserOverview;
