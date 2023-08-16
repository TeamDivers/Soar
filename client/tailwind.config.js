const colors = require('tailwindcss/colors');

/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ['./src/**/*.{js,jsx,ts,tsx}'],
    theme: {
        extend: {
            boxShadow: {
                md: '0px 0px 10px 0px rgba(0, 0, 0, 0.10);'
            }
        },
        colors: {
            ...colors,
            primary: '#1D5CFF'
        }
    },
    plugins: [],
    important: true
};
