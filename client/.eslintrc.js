module.exports = {
    env: {
        browser: true,
        es2021: true
    },
    extends: ['plugin:react/recommended', 'prettier', 'plugin:import/recommended'],
    overrides: [],
    parser: '@typescript-eslint/parser',
    parserOptions: {
        ecmaVersion: 'latest',
        sourceType: 'module'
    },
    plugins: ['react', '@typescript-eslint', 'prettier', 'import'],
    rules: {
        'prettier/prettier': 'error',
        'import/no-unresolved': 'off',
        'import/order': [
            'error',
            {
                groups: ['builtin', 'external', 'internal']
            }
        ]
    }
};
