import React from 'react';

interface ChipProps {
    label: string;
    onClick: () => void;
    isSelected?: boolean;
}

const Chip: React.FC<ChipProps> = ({ label, isSelected = false, onClick }) => {
    return (
        <button
            onClick={onClick}
            className={`chip inline-flex items-center px-3 py-1 rounded-full ${
                isSelected
                    ? 'bg-primary text-white text-sm font-semibold'
                    : 'border border-stone-300 bg-white text-zinc-500 text-sm font-medium'
            } min-w-fit `}
        >
            {label}
        </button>
    );
};

export default Chip;
