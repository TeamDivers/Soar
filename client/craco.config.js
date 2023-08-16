const path = require('path');

const aliases = require('./tsconfig.paths.json').compilerOptions.paths;

const alias = Object.entries(aliases).reduce(
    (acc, [aliasWithWildcard, [sourceWithWildcard]]) => {
        const alias = aliasWithWildcard.slice(0, -2);
        const source = sourceWithWildcard.slice(0, -2);
        acc[alias] = path.resolve(__dirname, source);
        return acc;
    },
    {}
);

module.exports = {
    webpack: {
        alias,
        configure: {
            optimization: {
                minimize: false
            }
        }
    }
};
