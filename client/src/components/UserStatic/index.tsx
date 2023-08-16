import React from 'react';

const UserStatic = () => {
    return (
        <>
            <div className="flex w-[361px] h-[71px] bg-zinc-100 rounded-[10px] justify-around mt-[20px]">
                <div>
                    <div className="text-center text-neutral-500 text-sm font-medium mt-[13px]">
                        학습기록
                    </div>
                    <div className="text-center text-black text-lg font-bold">
                        23
                    </div>
                </div>
                <div>
                    <div className="text-center text-neutral-500 text-sm font-medium mt-[13px]">
                        보유 포트폴리오
                    </div>
                    <div className="text-center text-black text-lg font-bold">
                        7
                    </div>
                </div>
                <div>
                    <div className="text-center text-neutral-500 text-sm font-medium mt-[13px]">
                        평점평균
                    </div>
                    <div className="text-center text-black text-lg font-bold">
                        3.72
                    </div>
                </div>
            </div>
        </>
    );
};

export default UserStatic;
