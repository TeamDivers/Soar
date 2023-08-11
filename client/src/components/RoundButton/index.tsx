import React from 'react';

interface RoundButtonProps {
    children: React.ReactNode;
}

const RoundButton = ({ children }: RoundButtonProps) => {
    return (
        <div className="bg-primary rounded-[50px] flex justify-center items-center py-[14px]">
            <span className="text-base font-bold text-white">{children}</span>
        </div>
    );
};

export default RoundButton;
