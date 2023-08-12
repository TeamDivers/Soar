import React from 'react';

import SlideToDelete from '@components/SlideToDelete';

const Portfolio = () => {
    return (
        <div>
            <div>{/* Header */}</div>
            <SlideToDelete
                onDelete={function (): void {
                    console.log('hi');
                }}
            >
                <div className="flex w-full p-3 shadow rounded-[10px] z-50">
                    hi
                </div>
            </SlideToDelete>
        </div>
    );
};

export default Portfolio;
