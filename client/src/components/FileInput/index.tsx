import React, { useRef, useState } from 'react';

interface FileInputProps {
    label: string;
}

const FileInput = ({ label }: FileInputProps) => {
    const inputRef = useRef<HTMLInputElement | null>(null);
    const [selectedFile, setSelectedFile] = useState<File | null>(null);

    const onClickButton = () => {
        if (inputRef.current) {
            inputRef.current.click();
        }
    };

    const onFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const file = event.target.files?.[0];

        if (file) setSelectedFile(file);
    };

    return (
        <>
            {!selectedFile && (
                <button
                    onClick={onClickButton}
                    className={`w-full h-10 rounded-[10px] flex justify-center items-center border border-dotted ${
                        selectedFile
                            ? 'bg-primary text-white'
                            : 'border-neutral-300'
                    } text-sm font-semibold`}
                >
                    {label} 추가 +
                </button>
            )}

            <input
                type="file"
                ref={inputRef}
                onChange={onFileChange}
                className="absolute invisible"
            />
            {selectedFile && (
                <div className="mt-2">
                    <img
                        src={URL.createObjectURL(selectedFile)}
                        alt="Selected"
                        className="object-cover w-full aspect-square"
                    />
                </div>
            )}
        </>
    );
};

export default FileInput;
