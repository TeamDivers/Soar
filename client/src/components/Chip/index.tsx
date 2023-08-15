import React from 'react';

interface ChipProps {
    label: string;
    onClick: () => void;
    isSelected?: boolean;
}

const Chip: React.FC<ChipProps> = ({ label, isSelected = false, onClick }) => {
    const chipClasses = isSelected
        ? 'bg-primary text-white'
        : 'border border-stone-300 bg-white';

    return (
        <div
            className={`chip inline-flex items-center px-3 py-1 m-1 rounded-full ${chipClasses} min-w-fit`}
        >
            {label}
        </div>
    );
};

export default Chip;
