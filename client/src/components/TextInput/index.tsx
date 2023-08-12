import React, { ChangeEvent, useState } from 'react';

interface TextInputProps {
    value: string;
    onChange: (value: string) => void;
    placeholder?: string;
}

const TextInput: React.FC<TextInputProps> = ({
    value,
    onChange,
    placeholder
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
            className="w-full rounded-[10px] border border-neutral-300 py-5 px-6 focus:outline-primary focus:border-primary"
        />
    );
};

export default TextInput;
