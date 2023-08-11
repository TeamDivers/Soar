import React from 'react';

interface DividerProps {
    direction?: 'horizontal' | 'vertical';
    length?: number;
    isFull?: boolean;
}

const Divider = ({
    direction = 'horizontal',
    length,
    isFull
}: DividerProps) => {
    const dividerStyle: React.CSSProperties = {
        width: direction === 'vertical' ? 1 : length || '100%',
        height: direction === 'horizontal' ? 1 : length || '100%',
        backgroundColor: '#CFCFCF', // You can change the color here
        flex: isFull ? 1 : 'none'
    };

    return <div style={dividerStyle} />;
};

export default Divider;
