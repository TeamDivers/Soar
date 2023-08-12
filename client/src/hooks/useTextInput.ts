import { useState } from 'react';

function useTextInput(initialValue = '') {
    const [inputValue, setInputValue] = useState(initialValue);

    const handleInputChange = (value: string) => {
        setInputValue(value);
    };

    return {
        value: inputValue,
        onChange: handleInputChange
    };
}

export default useTextInput;
