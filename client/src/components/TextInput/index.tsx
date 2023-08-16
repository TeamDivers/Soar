import React, { ChangeEvent, useState } from 'react';

interface TextInputProps {
    value: string;
    onChange: (value: string) => void;
    placeholder?: string;
    size?: 'lg' | 'sm';
}

const TextInput: React.FC<TextInputProps> = ({
    value,
    onChange,
    placeholder,
    size = 'lg'
}) => {
    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        onChange(event.target.value);
    };

    return (
        <input
            type="text"
            value={value}
            onChange={handleChange}
            placeholder={placeholder}
            className={`w-full font-normal rounded-[10px] border border-neutral-300 focus:outline-primary focus:border-primary ${
                size === 'lg' ? 'py-5 px-6 text-base' : 'p-4 text-sm'
            }`}
        />
    );
};

export default TextInput;
