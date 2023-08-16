import React from 'react';

interface IndicatorProps {
    progress: number;
}

const Indicator = ({ progress }: IndicatorProps) => {
    return (
        <div className="flex items-center justify-center bg-gray-100">
            <div className="w-full bg-gray-300">
                <div
                    className="h-[2px] bg-blue-500"
                    style={{ width: `${progress}%` }}
                ></div>
            </div>
        </div>
    );
};

export default Indicator;
