import React, { useEffect } from 'react';

import { useCreateRecord } from '@apis/record/createRecord';

const RecordCreate = () => {
    const { mutate } = useCreateRecord();

    useEffect(() => {
        const base64ImageData =
            'data:image/png;base64, iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==';

        // Convert base64 to blob
        const byteCharacters = atob(base64ImageData.split(',')[1]);
        const byteArray = new Uint8Array(byteCharacters.length);

        for (let i = 0; i < byteCharacters.length; i++) {
            byteArray[i] = byteCharacters.charCodeAt(i);
        }

        const imageBlob = new Blob([byteArray], { type: 'image/png' });

        // Create a new File from the Blob
        const imageFile = new File([imageBlob], 'image.png', {
            type: 'image/png'
        });

        mutate({
            category: 'category',
            content: 'content',
            isPublic: false,
            startDate: new Date().toString(),
            endDate: new Date().toString(),
            tagName: 'tagName',
            memberId: 0,
            files: [imageFile]
        });
    }, []);

    return <div>RecordCreate</div>;
};

export default RecordCreate;
