import { useState } from 'react';

const useBottomSheet = () => {
    const [isOpen, setIsOpen] = useState(false);

    const toggle = () => setIsOpen((prev) => !prev);

    const close = () => setIsOpen(false);

    return { isOpen, toggle, close };
};

export default useBottomSheet;
