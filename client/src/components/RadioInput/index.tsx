import React, { useState } from 'react';

interface RadioInputProps {
    options: { label: string; value: string }[];
    selectedValue: string;
    onChange: (value: string) => void;
}

const RadioInput: React.FC<RadioInputProps> = ({
    options,
    selectedValue,
    onChange
}) => {
    return (
        <div className="flex flex-col gap-[26px]">
            {options.map((option) => (
                <label
                    key={option.value}
                    className="flex items-center space-x-2"
                >
                    <input
                        type="radio"
                        value={option.value}
                        checked={selectedValue === option.value}
                        onChange={() => onChange(option.value)}
                        className="w-5 h-5 border-[5px] border-neutral-200 bg-neutral-300 rounded-full appearance-none checked:bg-blue-500 checked:border-blue-100"
                    />
                    <span className="text-base font-medium text-neutral-500">
                        {option.label}
                    </span>
                </label>
            ))}
        </div>
    );
};

export default RadioInput;
