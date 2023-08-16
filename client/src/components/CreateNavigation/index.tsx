import React from 'react';

interface CreateNavigationProps {
    onCancel: () => void;
    onSave: () => void;
}

const CreateNavigation = ({ onCancel, onSave }: CreateNavigationProps) => {
    return (
        <div className="fixed bottom-0 z-50 flex items-center justify-between w-full max-w-md px-4 py-5 mx-auto bg-white drop-shadow gap-[10px]">
            <button
                onClick={onCancel}
                className="py-[15px] rounded-full border border-neutral-300 grow-[2] text-stone-900 text-lg font-semibold"
            >
                취소하기
            </button>
            <button
                onClick={onSave}
                className="py-[15px] rounded-full bg-blue-600 grow-[3] text-white text-lg font-semibold"
            >
                저장하기
            </button>
        </div>
    );
};

export default CreateNavigation;
