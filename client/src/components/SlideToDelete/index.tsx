import React, { useEffect, useRef, useState } from 'react';

interface SlideToDeleteProps {
    onDelete: () => void;
    children: React.ReactNode;
}

const SlideToDelete: React.FC<SlideToDeleteProps> = ({
    onDelete,
    children
}) => {
    return (
        <div className="relative flex items-center justify-between group">
            {children}
            <div
                className={`absolute top-0 right-0 h-full bg-red-500 text-white flex items-center px-4`}
            >
                Delete
            </div>
        </div>
    );
};

export default SlideToDelete;
