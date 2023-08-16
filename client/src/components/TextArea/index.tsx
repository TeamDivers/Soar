import React, { ChangeEvent } from 'react';

interface TextAreaProps {
    value: string;
    onChange: (value: string) => void;
    placeholder?: string;
}

const TextArea = ({ value, onChange, placeholder }: TextAreaProps) => {
    const handleChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
        onChange(event.target.value);
    };

    return (
        <textarea
            rows={4}
            className="w-full rounded-[10px] border border-neutral-300 p-4"
            value={value}
            onChange={handleChange}
            placeholder={placeholder}
        />
    );
};

export default TextArea;
