import React, { useEffect, useState } from 'react';
import { Swiper, SwiperSlide, useSwiper, useSwiperSlide } from 'swiper/react';

import 'swiper/css';
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
        <button className="flex items-center justify-center h-40 px-4 mt-2 rounded-[10px] bg-red-600">
            <Delete />
        </button>
    );
};

export default SlideToDelete;
