import React, { useEffect, useState } from 'react';
import { Swiper, SwiperSlide, useSwiper, useSwiperSlide } from 'swiper/react';

import 'swiper/css';
import './index.css';
import { Delete } from '@images/index';

interface SlideToDeleteProps {
    onDelete: () => void;
    children: React.ReactNode;
}

const SlideToDelete: React.FC<SlideToDeleteProps> = ({
    onDelete,
    children
}) => {
    return (
        <Swiper centeredSlides={true}>
            <SwiperSlide>{children}</SwiperSlide>
            <SwiperSlide>
                <DeleteSlide onDelete={onDelete} />
            </SwiperSlide>
        </Swiper>
    );
};

const DeleteSlide = ({ onDelete }: { onDelete: () => void }) => {
    const swiperSlide = useSwiperSlide();
    const swiper = useSwiper();

    useEffect(() => {
        if (swiperSlide.isActive) {
            swiper.slidePrev();
            onDelete();
        }
    }, [swiperSlide.isActive]);

    return (
        <div className="h-full py-2">
            <button className="flex items-center justify-center h-full px-4 rounded-[10px] bg-red-600">
                <Delete />
            </button>
        </div>
    );
};

export default SlideToDelete;
