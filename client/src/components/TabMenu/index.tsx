import { Box, Tab, Tabs } from '@mui/material';
import React, { useState } from 'react';

import './index.css';

interface TabProps {
    labels: string[];
    value: number;
    onTabChange: (tabIndex: number) => void;
    children: React.ReactNode;
}

const TabMenu = ({ labels, value, onTabChange, children }: TabProps) => {
    const handleChange = (
        event: React.SyntheticEvent<Element, Event>,
        value: any
    ) => {
        onTabChange(value);
    };

    return (
        <div>
            <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                <Tabs
                    value={value}
                    onChange={handleChange}
                    aria-label="basic tabs example"
                >
                    {labels.map((label, index) => {
                        return (
                            <Tab
                                key={label}
                                label={label}
                                className={`py-2 ${
                                    index === value
                                        ? 'text-black text-base font-semibold leading-[21px]'
                                        : 'text-neutral-400 text-base font-semibold leading-[21px]'
                                }`}
                            />
                        );
                    })}
                </Tabs>
            </Box>
            {children}
        </div>
    );
};

export default TabMenu;
