import React, { ForwardedRef, forwardRef, useCallback, useState } from 'react';

type RatingProps = {
    value: number;
    onChange?: (value: number) => void;
};

const MIN = 0;
const MAX = 5;
const STEP = 0.5;

function isNil(value: any): value is null | undefined {
    return value === null || value === undefined;
}

const Rating = forwardRef(
    (
        { value = 0, onChange }: RatingProps,
        ref: ForwardedRef<HTMLDivElement>
    ) => {
        const handleSliderChange = useCallback(
            (e: React.ChangeEvent<HTMLInputElement>) => {
                onChange?.(parseFloat(e.target.value));
            },
            [onChange]
        );

        return (
            <div ref={ref} className="">
                <div className="">
                    <div className="">
                        {Array(MAX)
                            .fill(0)
                            .map((_, index) => index + 1)
                            .map((starValue, index) => (
                                <span key={index} className="">
                                    {/* <Icon
                                        // name="HalfStar"
                                        size={12}
                                        color={
                                            !isNil(value) &&
                                            starValue - STEP <= value
                                                ? 'purple'
                                                : 'grey200'
                                        }
                                    />
                                    <Icon
                                        // className={cx('half-star--flipped')}
                                        name="HalfStar"
                                        size={12}
                                        color={
                                            !isNil(value) && starValue <= value
                                                ? 'purple'
                                                : 'grey200'
                                        }
                                    /> */}
                                </span>
                            ))}
                    </div>
                    <input
                        className=""
                        type="range"
                        min={MIN}
                        max={MAX}
                        step={STEP}
                        value={value}
                        onChange={handleSliderChange}
                    />
                </div>
            </div>
        );
    }
);
Rating.displayName = 'Rating';

export default Rating;
