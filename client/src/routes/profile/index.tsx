import React from 'react';

import PortfolioCard from '@components/PortfolioCard';
import ProfileView from '@components/ProfileView';
import UserOverview from '@components/UserOverview';
import UserStatic from '@components/UserStatic';

const Profile = () => {
    return (
        <div className="px-4">
            <div>Profile</div>
            <ProfileView></ProfileView>
            <div className="flex flex-col justify-center items-center">
                <UserStatic />
                <UserOverview
                    career="대학생/ 건국대학교"
                    education="건국대"
                    email="jinho9940@gmail.com"
                    id={1}
                    name="전진호"
                    phoneNumber="010-2370-9940"
                    thumbnail="s3s3s3"
                />
            </div>
            <div className="text-black text-xl font-bold mt-[30px] mb-[16px]">
                전진호님의 포트폴리오
            </div>
            <div className="flex-col">
                {/*<PortfolioCard />*/}
                <div className="py-[12px]" />
                {/*<PortfolioCard />*/}
            </div>
        </div>
    );
};

export default Profile;
