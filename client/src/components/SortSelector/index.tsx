import React, { useState } from 'react';

import BottomSheet from '@components/BottomSheet';
import Divider from '@components/Divider';

import { Check, Down } from '@images/index';

import useBottomSheet from '@hooks/useBottomSheet';

interface SortSelectorProps {
    onChange: (v: string) => void;
}

const SORTS = ['최신순', '오래된순', '공개만', '비공개만', '랭킹순'];

const SortSelector = ({ onChange }: SortSelectorProps) => {
    const [sort, setSort] = useState<(typeof SORTS)[number]>(SORTS[0]);
    const { isOpen, toggle, close } = useBottomSheet();

    const onClickSort = (v: (typeof SORTS)[number]) => {
        setSort(v);
        toggle();
        onChange(v);
    };

    return (
        <>
            <button
                type="button"
                onClick={toggle}
                className="flex justify-center items-center py-[6px] px-3 gap-[6px] rounded-[50px] border border-primary"
            >
                <div className="text-sm font-medium text-primary">{sort}</div>
                <Down />
            </button>
            <BottomSheet
                isOpen={isOpen}
                onClose={close}
                toggle={toggle}
                snapPoint={0.5}
            >
                <div className="p-4">
                    {SORTS.map((v, idx) => {
                        return (
                            <div key={v}>
                                <div
                                    className="flex items-center justify-between py-5 px-[10px]"
                                    onClick={() => onClickSort(v)}
                                >
                                    <span className="text-base font-semibold text-black">
                                        {v}
                                    </span>
                                    {sort === v && <Check />}
                                </div>
                                {idx !== SORTS.length - 1 && <Divider isFull />}
                            </div>
                        );
                    })}
                </div>
            </BottomSheet>
        </>
    );
};

export default SortSelector;
