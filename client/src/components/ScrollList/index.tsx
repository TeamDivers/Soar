import React from 'react';

interface ScrollListProps {
    children: React.ReactNode;
}

const ScrollList = ({ children }: ScrollListProps) => {
    return (
        <div className="flex overflow-x-auto hide-scrollbar">{children}</div>
    );
};

export default ScrollList;
