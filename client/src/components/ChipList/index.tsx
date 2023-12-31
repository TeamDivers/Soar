import React, { useState } from 'react';

import Chip from '@components/Chip';
import ScrollList from '@components/ScrollList';

interface ChipListProps {
    chips: string[];
    onClick: (label: string) => void;
}

const ChipList: React.FC<ChipListProps> = ({ chips, onClick }) => {
    const [selectedIdx, setSelectedIdx] = useState(0);

    const handleOnClickChip = (index: number) => {
        onClick(chips[index]);
        setSelectedIdx(index);
    };

    return (
        <ScrollList>
            <div className="flex gap-2">
                <div className="p-2" />
                {chips.map((chip, index) => (
                    <Chip
                        key={index}
                        label={chip}
                        isSelected={index === selectedIdx}
                        onClick={() => handleOnClickChip(index)}
                    />
                ))}
                <div className="p-2" />
            </div>
        </ScrollList>
    );
};

export default ChipList;
