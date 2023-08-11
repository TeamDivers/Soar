import React from 'react';

interface RoundButtonProps {
    children: React.ReactNode;
    onClick: () => void;
    backgroundColor?: string;
    textColor?: string;
    hasBorder?: boolean;
    borderColor?: string;
}

const RoundButton = ({
    children,
    onClick,
    backgroundColor = 'primary',
    textColor = 'white',
    hasBorder,
    borderColor = '#DDDDDD'
}: RoundButtonProps) => {
    return (
        <button
            type="button"
            onClick={onClick}
            className={`bg-primary rounded-[50px] flex justify-center w-full items-center py-[14px]`}
            style={{
                backgroundColor,
                borderColor,
                borderWidth: hasBorder ? 1 : 0
            }}
        >
            <span
                className={`text-base font-bold text-white`}
                style={{ color: textColor }}
            >
                {children}
            </span>
        </button>
    );
};

export default RoundButton;
