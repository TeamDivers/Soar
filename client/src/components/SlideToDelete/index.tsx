import React, { useEffect, useRef, useState } from 'react';
import { Swiper, SwiperSlide, useSwiper, useSwiperSlide } from 'swiper/react';

import 'swiper/css';
import './index.css';
import { Delete } from '@images/index';

import { useIntersectionObserver } from '@hooks/useIntersectionObserver';

interface SlideToDeleteProps {
    onDelete: () => void;
    children: React.ReactNode;
}

const SlideToDelete: React.FC<SlideToDeleteProps> = ({
    onDelete,
    children
}) => {
    return (
        <Swiper
            spaceBetween={0}
            resistance={true}
            resistanceRatio={0}
            slidesPerView={'auto'}
        >
            <SwiperSlide className="first-slide">{children}</SwiperSlide>
            <SwiperSlide className="last-slide">
                <DeleteSlide onDelete={onDelete} />
            </SwiperSlide>
        </Swiper>
    );
};

const DeleteSlide = ({ onDelete }: { onDelete: () => void }) => {
    const swiper = useSwiper();

    const ref = useRef<HTMLDivElement | null>(null);
    const entry = useIntersectionObserver(ref, {
        threshold: 0.4
    });
    const [isVisible, setIsVisible] = useState(false);

    let timer: NodeJS.Timeout;

    useEffect(() => {
        if (entry?.isIntersecting) {
            timer = setTimeout(() => {
                setIsVisible(true);
            }, 800);
        } else {
            if (timer) clearTimeout(timer);
            setIsVisible(false);
        }

        return () => {
            clearTimeout(timer);
        };
    }, [entry?.isIntersecting]);

    useEffect(() => {
        if (isVisible) {
            swiper.slidePrev();
            onDelete();
        }
    }, [isVisible]);

    return (
        <div ref={ref} className="h-full py-2">
            <button className="flex items-center justify-center h-full px-4 rounded-[10px] bg-red-600">
                <Delete />
            </button>
        </div>
    );
};

export default SlideToDelete;
