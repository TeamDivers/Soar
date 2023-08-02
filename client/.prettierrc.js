module.exports = {
    singleQuote: true,
    printWidth: 200,
    proseWrap: 'always',
    tabWidth: 4,
    useTabs: false,
    trailingComma: 'none',
    bracketSpacing: true,
    semi: true,
    plugins: [require.resolve('@trivago/prettier-plugin-sort-imports')],
    importOrder: ['^@routes/(.*)$', '^@components/(.*)$', '^[./]'],
    importOrderSeparation: true,
    importOrderSortSpecifiers: true
};
