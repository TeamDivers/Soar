import React, { useState } from 'react';

import Chip from '@components/Chip';

interface ChipListProps {
    chips: string[];
}

const ChipList: React.FC<ChipListProps> = ({ chips }) => {
    const [selectedIdx, setSelectedIdx] = useState(0);

    return (
        <div className="flex overflow-x-auto hide-scrollbar">
            <div className="flex">
                <div className="p-2" />
                {chips.map((chip, index) => (
                    <Chip
                        key={index}
                        label={chip}
                        isSelected={index === selectedIdx}
                        onClick={function (): void {
                            throw new Error('Function not implemented.');
                        }}
                    />
                ))}
                <div className="p-2" />
            </div>
        </div>
    );
};

export default ChipList;
