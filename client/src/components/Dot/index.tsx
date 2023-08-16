import React from 'react';

interface DotProps {
    color: string;
    size: number;
}

const Dot = ({ color, size }: DotProps) => {
    return (
        <div
            className="rounded-full"
            style={{ backgroundColor: color, width: size, height: size }}
        ></div>
    );
};

export default Dot;
