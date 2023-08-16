import moment from 'moment';
import React, { useRef, useState } from 'react';

interface DateTimePickerProps {
    value: any;
    onChange: any;
}

const DateTimePicker = ({}: DateTimePickerProps) => {
    const inputRef = useRef<HTMLInputElement | null>(null);
    const [selectedDate, setSelectedDate] = useState<string>(''); // Store selected date here

    const handleDivClick = () => {
        if (inputRef.current) {
            inputRef.current.showPicker(); // Simulate click on hidden input
        }
    };

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSelectedDate(event.target.value); // Update selected date
    };

    return (
        <>
            <div
                onClick={handleDivClick}
                className="w-full p-2 border cursor-pointer bg-neutral-200 rounded-[30px] h-8 flex items-center justify-center text-zinc-700 text-sm font-normal"
            >
                {selectedDate
                    ? moment(selectedDate).format('YYYY.MM.DD, hh:mma')
                    : ''}
            </div>
            <input
                type="datetime-local"
                ref={inputRef}
                value={selectedDate}
                onChange={handleInputChange}
                className="absolute invisible"
            />
        </>
    );
};

export default DateTimePicker;
