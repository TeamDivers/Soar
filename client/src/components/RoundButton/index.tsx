import React from 'react';

interface RoundButtonProps {
    children: React.ReactNode;
    onClick: () => void;
    backgroundColor?: string;
    hasBorder?: boolean;
    borderColor?: string;
}

const RoundButton = ({
    children,
    onClick,
    backgroundColor = 'primary',
    hasBorder,
    borderColor = '#DDDDDD'
}: RoundButtonProps) => {
    return (
        <button
            type="button"
            onClick={onClick}
            className={`bg-primary rounded-[50px] flex justify-center w-full items-center text-white text-xl font-bold`}
            style={{
                backgroundColor,
                borderColor,
                borderWidth: hasBorder ? 1 : 0
            }}
        >
            {children}
        </button>
    );
};

export default RoundButton;
