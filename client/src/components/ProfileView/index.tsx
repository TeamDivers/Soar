import React from 'react';

const ProfileView = () => {
    return (
        <div className="flex mx-[16px]">
            <div className="flex gap-[18px] items-center">
                <img
                    src={'https://placehold.co/200'}
                    className="rounded-full w-[100px] h-[100px]"
                />
                <div className="flex flex-col gap-[3px]">
                    <span className="text-xl font-bold text-black">전진호</span>
                    <span className="text-sm font-normal text-neutral-400">
                        남자 / 22세 / 학생
                    </span>
                </div>
            </div>
        </div>
    );
};

export default ProfileView;
