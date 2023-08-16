import React from 'react';

import { Plus } from '@images/index';

const PlusButton = ({ onClick }: { onClick: () => void }) => {
    return (
        <button
            className="w-full h-10 bg-stone-300 rounded-[10px] flex justify-center items-center"
            onClick={onClick}
        >
            <Plus style={{ width: 18, height: 18 }} />
        </button>
    );
};

export default PlusButton;
