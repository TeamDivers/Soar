const colors = require('tailwindcss/colors');

/** @type {import('tailwindcss').Config} */
module.exports = {
    mode: 'jit',
    // These paths are just examples, customize them to match your project structure
    purge: ['./public/**/*.html', './src/**/*.{js,jsx,ts,tsx,vue}'],
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
