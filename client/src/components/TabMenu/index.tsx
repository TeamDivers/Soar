import { Box, Tab, Tabs } from '@mui/material';
import React, { useState } from 'react';

interface TabProps {
    labels: string[];
}

const TabMenu = ({ labels }: TabProps) => {
    const [currentTab, setCurrentTab] = useState(0);

    const handleChange = (
        event: React.SyntheticEvent<Element, Event>,
        value: any
    ) => {
        setCurrentTab(value);
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
                </Tabs>
            </Box>
            <TabItem visible={currentTab === 0}>Item One</TabItem>
            <TabItem visible={currentTab === 1}>Item Two</TabItem>
        </div>
    );
};

interface TabItemProps {
    visible: boolean;
    children: React.ReactNode;
}

const TabItem = ({ visible, children }: TabItemProps) => {
    if (!visible) {
        return <></>;
    }

    return <div>{children}</div>;
};

export default TabMenu;
