import { useState } from 'react';

interface RadioInputOption {
    label: string;
    value: string;
}

interface UseRadioInputOptions {
    initialSelectedValue?: string;
    options: RadioInputOption[];
}

interface UseRadioInputResult {
    selectedValue: string;
    handleOptionChange: (value: string) => void;
}

const useRadioInput = ({
    initialSelectedValue = '',
    options
}: UseRadioInputOptions): UseRadioInputResult => {
    const [selectedValue, setSelectedValue] = useState(initialSelectedValue);

    const handleOptionChange = (value: string) => {
        setSelectedValue(value);
    };

    return { selectedValue, handleOptionChange };
};

export default useRadioInput;
