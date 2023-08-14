import React from 'react';
import { default as ReactModal } from 'react-modal';

interface ModalProps {
    isOpen: boolean;
    close: () => void;
    children: React.ReactNode;
}

const customStyles = {
    overlay: {
        background: 'rgba(0, 0, 0, 0.40)',
        backdropFilter: 'blur(3px)',
        zIndex: 100
    },
    content: {
        top: '50%',
        left: '50%',
        right: 'auto',
        bottom: 'auto',
        marginRight: '-50%',
        transform: 'translate(-50%, -50%)',
        border: 'none',
        padding: 0,
        background: 'none'
        // position: absolute;
        // inset: 50% auto auto 50%;
        // border: none;
        // background: rgb(255, 255, 255);
        // overflow: auto;
        // border-radius: 4px;
        // outline: none;
        // padding: 20px;
        // margin-right: -50%;
        // transform: translate(-50%, -50%);
    }
};

const Modal = ({ isOpen, close, children }: ModalProps) => {
    return (
        <ReactModal
            isOpen={isOpen}
            onRequestClose={close}
            style={customStyles}
            contentLabel="Example Modal"
        >
            {children}
        </ReactModal>
    );
};

export default Modal;
