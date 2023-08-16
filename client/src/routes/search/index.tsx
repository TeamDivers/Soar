import React, { ChangeEvent, useState } from 'react';

import Layout from '@components/layout';
import PortfolioCard from '@components/PortfolioCard';
import ScrollList from '@components/ScrollList';
import TabMenu from '@components/TabMenu';

import { Left, Search as SearchIcon } from '@images/index';

import useTextInput from '@hooks/useTextInput';

const Search = () => {
    const [tabIndex, setTabIndex] = useState(0);
    const { value: searchKeyword, onChange: onChangeSearchKeyword } =
        useTextInput();

    const portfolioSearchResults = [{ id: 1 }, { id: 2 }];

    const userSearchResults = [{ id: 1 }, { id: 2 }];

    /** TODO: retrieve recentlySearchedKeywords from localStorage */
    const recentlySearchedKeywords = [
        '디자인',
        '수학',
        '개발',
        '캘리그라피',
        '영어',
        '영어1',
        '영어2',
        '영어3',
        '영어4'
    ];

    const renderPortfolioSearchResult = () => {
        return (
            <div className="flex flex-col gap-3 p-4">
                <PortfolioCard ranking={12} size="sm" />
                <PortfolioCard ranking={88} size="sm" />
            </div>
        );
    };

    const renderUserSearchResult = () => {
        return <>u</>;
    };

    return (
        <Layout hasHeader={false}>
            <div
                className={`flex items-center justify-between gap-4 px-4 pt-4 ${
                    searchKeyword.length > 0 ? 'mb-[8px]' : 'mb-6'
                }`}
            >
                <button>
                    <Left style={{ width: 8 }} />
                </button>
                <SearchInput
                    value={searchKeyword}
                    onChange={onChangeSearchKeyword}
                />
            </div>
            {searchKeyword.length > 0 ? (
                <TabMenu
                    labels={['포트폴리오', '유저 프로필']}
                    value={tabIndex}
                    onTabChange={setTabIndex}
                >
                    {tabIndex === 0
                        ? renderPortfolioSearchResult()
                        : renderUserSearchResult()}
                </TabMenu>
            ) : (
                <Section title={'최근 검색어'}>
                    <ScrollList>
                        <div className="flex gap-2">
                            <div className="p-0" />
                            {recentlySearchedKeywords.map((keyword) => {
                                return (
                                    <div
                                        key={keyword}
                                        className="min-w-fit px-3 py-1 text-sm font-medium text-black bg-gray-200 rounded-[20px] justify-center items-center"
                                    >
                                        {keyword}
                                    </div>
                                );
                            })}
                            <div className="p-2" />
                        </div>
                    </ScrollList>
                </Section>
            )}
        </Layout>
    );
};

export default Search;

const SearchInput = ({
    value,
    onChange
}: {
    value: string;
    onChange: (value: string) => void;
}) => {
    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        onChange(event.target.value);
    };

    return (
        <div className="relative w-full ">
            <input
                className="bg-zinc-100 rounded-[42px] py-[6px] px-3 w-full placeholder:text-zinc-400 placeholder:text-sm"
                placeholder="공부 분야나 닉네임으로 검색해보세요."
                type="text"
                value={value}
                onChange={handleChange}
            />
            <div className="absolute right-3 top-[7px]">
                <SearchIcon
                    stroke={'#949494'}
                    style={{ width: 20, height: 20 }}
                />
            </div>
        </div>
    );
};

const Section = ({
    title,
    children
}: {
    title: string;
    children: React.ReactNode;
}) => {
    return (
        <div className="flex flex-col gap-[10px] mb-7">
            <span className="px-4 text-neutral-400 text-xs font-semibold leading-[21px]">
                {title}
            </span>
            {children}
        </div>
    );
};
