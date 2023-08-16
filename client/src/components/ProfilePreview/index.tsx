import React from 'react';
import { useNavigate } from 'react-router-dom';

interface ProfilePreview {
    id: string;
    image: string;
    name: string;
    desc: string;
}

const ProfilePreview = ({ id, image, name, desc }: ProfilePreview) => {
    /** TODO: navigate to user profile on click */
    const navigate = useNavigate();

    const onClickPreview = () => {
        navigate(`/user/${id}`);
    };
    return (
        <div className="flex gap-[10px] items-center" onClick={onClickPreview}>
            <img
                src={'https://placehold.co/200'}
                className="rounded-full w-[42px] h-[42px]"
            />
            <div className="flex flex-col gap-[3px]">
                <span className="text-base font-bold text-black">전진호</span>
                <span className="text-xs font-normal text-neutral-400">
                    #개발자 #프론트앤드
                </span>
            </div>
        </div>
    );
};

export default ProfilePreview;
