import React from 'react';
import Sheet from 'react-modal-sheet';

import '@styles/sheet.css';

interface BottomSheetProps {
    isOpen: boolean;
    onClose: () => void;
    toggle: () => void;
    snapPoint?: number;
    children: React.ReactNode;
}

const BottomSheet = ({
    isOpen,
    onClose,
    toggle,
    snapPoint = 0.8,
    children
}: BottomSheetProps) => {
    return (
        <Sheet
            isOpen={isOpen}
            onClose={onClose}
            snapPoints={[snapPoint, 0]}
            disableDrag
        >
            <Sheet.Container>
                <Sheet.Header />
                <Sheet.Content>{children}</Sheet.Content>
            </Sheet.Container>
            <Sheet.Backdrop onTap={toggle} />
        </Sheet>
    );
};

export default BottomSheet;
