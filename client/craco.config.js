const path = require('path');
module.exports = {
    webpack: {
        alias: {
            '@routes': path.resolve(__dirname, 'src/routes'),
            '@components': path.resolve(__dirname, 'src/components'),
            '@configs': path.resolve(__dirname, 'src/configs')
        }
    }
};
