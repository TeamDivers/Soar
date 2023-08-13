export const text2Color = (text: string) => {
    let hash = 0;

    for (let i = 0; i < text.length; i++) {
        hash = text.charCodeAt(i) + ((hash << 5) - hash);
    }

    const r = (hash & 0xff0000) >> 16;
    const g = (hash & 0x00ff00) >> 8;
    const b = hash & 0x0000ff;

    return `#${((1 << 24) | (r << 16) | (g << 8) | b).toString(16).slice(1)}`;
};
