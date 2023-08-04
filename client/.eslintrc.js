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
        'sort-imports': [
            'error',
            {
                ignoreCase: true,
                ignoreDeclarationSort: true,
                ignoreMemberSort: false,
                allowSeparatedGroups: true
            }
        ],
        'import/no-unresolved': 'off',
        'import/order': [
            'error',
            {
                'newlines-between': 'always',
                groups: ['type', 'builtin', 'external', 'internal', 'parent', 'sibling', 'index', 'unknown'],
                pathGroups: [
                    {
                        pattern: 'react*',
                        group: 'external',
                        position: 'before'
                    },
                    {
                        pattern: '@routes/*',
                        group: 'internal',
                        position: 'after'
                    },
                    {
                        pattern: '@configs/*',
                        group: 'internal',
                        position: 'after'
                    },
                    {
                        pattern: '@components/*',
                        group: 'internal',
                        position: 'after'
                    }
                ],
                alphabetize: {
                    order: 'asc',
                    caseInsensitive: true
                }
            }
        ]
    },
    settings: {
        'import/resolver': {
            webpack: {},
            typescript: {}
        }
    }
};
