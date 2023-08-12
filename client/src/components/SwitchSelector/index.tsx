import React, { useState } from 'react';

interface SwitchSelectorProps {
    options: string[];
    onChange: (selectedOption: string) => void;
}

const SwitchSelector: React.FC<SwitchSelectorProps> = ({
    options,
    onChange
}) => {
    const [activeIndex, setActiveIndex] = useState(options.indexOf(options[0]));

    const handleOptionChange = (index: number) => {
        setActiveIndex(index);
        onChange(options[index]);
    };

    return (
        <div className="relative flex rounded-full bg-zinc-100 w-fit">
            <div
                className={`absolute z-0 w-2/4 h-full transition-transform duration-300 rounded-full bg-primary ${
                    activeIndex === 1 && 'translate-x-full'
                }`}
            ></div>
            {options.map((option, index) => (
                <button
                    key={option}
                    className={`flex items-center justify-center rounded-full px-9 py-2 transition-colors duration-300 z-10 ${
                        activeIndex === index && 'text-white'
                    }`}
                    onClick={() => handleOptionChange(index)}
                >
                    {option}
                </button>
            ))}
        </div>
    );
};

export default SwitchSelector;
