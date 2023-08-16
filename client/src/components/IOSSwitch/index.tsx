import { Switch } from '@mui/material';
import React from 'react';

import './index.css';

interface IOSSwithcInterface {
    toggle: () => void;
}

const IOSSwitch = ({ toggle }: IOSSwithcInterface) => (
    <Switch
        className="IOSSwitch"
        focusVisibleClassName="focus-visible"
        disableRipple
        onClick={toggle}
    />
);

export default IOSSwitch;
