import { Box, Tab, Tabs } from '@mui/material';
import React, { useState } from 'react';

interface TabProps {
    labels: string[];
}

const TabMenu = ({ labels }: TabProps) => {
    const [currentTab, setCurrentTab] = useState(labels[0]);

    const handleChange = (
        event: React.SyntheticEvent<Element, Event>,
        value: any
    ) => {
        console.log('🚀 ~ file: index.tsx:12 ~ handleChange ~ value:', value);
        console.log('🚀 ~ file: index.tsx:12 ~ handleChange ~ event:', event);
    };

    return (
        <div>
            <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                <Tabs
                    value={currentTab}
                    onChange={handleChange}
                    aria-label="basic tabs example"
                >
                    <Tab label="Item One" />
                    <Tab label="Item Two" />
                    <Tab label="Item Three" />
                </Tabs>
            </Box>
            {/* <CustomTabPanel value={value} index={0}>
                Item One
            </CustomTabPanel>
            <CustomTabPanel value={value} index={1}>
                Item Two
            </CustomTabPanel>
            <CustomTabPanel value={value} index={2}>
                Item Three
            </CustomTabPanel> */}
        </div>
    );
};

export default TabMenu;
