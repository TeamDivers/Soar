import React, { useState } from 'react';
import { CompactPicker } from 'react-color';
import { useNavigate } from 'react-router-dom';

import { useCreatePortfolio } from '@apis/portfolio/createPortfolio';

import CreateNavigation from '@components/CreateNavigation';
import Divider from '@components/Divider';
import FileInput from '@components/FileInput';
import IOSSwitch from '@components/IOSSwitch';
import PlusButton from '@components/PlustButton';
import Project from '@components/Project';
import ProejctEdit, { ProjectType } from '@components/ProjectEdit.tsx';
import TextInput from '@components/TextInput';

import useTextInput from '@hooks/useTextInput';

import './index.css';

const PortfolioCreate = () => {
    const navigate = useNavigate();

    const { mutate } = useCreatePortfolio();

    const { value: title, onChange: onChangeTitle } = useTextInput();
    const [projects, setProjects] = useState<ProjectType[]>([]);
    const [isAddingPeoject, setIsAddingProject] = useState(true);
    /**
     * disabled when new empty projected is added
     * - setIsAddingProject(false) in onAddProject
     * enble this state when addEmptyProjectEdit is called
     */
    const [color, setColor] = useState('');
    const [isPublic, setIsPublic] = useState(true);
    const [file, setFile] = useState<File>();

    const addEmptyProjectEdit = () => {
        setIsAddingProject(true);
    };

    const handleOnAddProject = (project: ProjectType) => {
        setIsAddingProject(false);
        setProjects((prev) => [...prev, project]);
    };

    const handleColorChange = ({ hex }: { hex: string }) => {
        setColor(hex);
    };

    const handleOnSave = () => {
        if (file)
            mutate({
                portfolio: {
                    title,
                    description: '',
                    category: '',
                    isPublic,
                    memberId: 1,
                    tags: [],
                    background: color
                },
                projectList: [],
                files: [],
                thumbnail: file
            });
    };

    const handleOnChangeFileInput = (file: File) => {
        setFile(file);
    };

    return (
        <>
            <div className="flex flex-col justify-between px-4 pt-[34px] mb-2 overflow-y-scroll h-screen pb-[100px]">
                {/* user info section */}
                <div className="flex items-center gap-[22px] mb-[30px]">
                    <img
                        src="https://placehold.co/400/000000/FFF"
                        className="w-[82px] h-[82px] rounded-full"
                    />
                    <div className="flex flex-col gap-[6px] ">
                        <span className="text-lg font-normal text-black">
                            전진호
                        </span>
                        <span className="text-sm font-normal text-neutral-400">
                            대학생
                        </span>
                    </div>
                </div>
                <div className="flex flex-col gap-[14px] mb-[30px]">
                    <Desc
                        label={'학력'}
                        value={'대학생 / 건국대학교 컴퓨터공학과'}
                        isRequired
                    />
                    <Desc label={'휴대폰'} value={'010-2370-9940'} isRequired />
                    <Desc label={'이메일'} value={'jinho9940@gmail.com'} />
                    <Desc label={'경력'} value={'wit 2기 졸업 외 6개'} />
                </div>
                <button
                    className="rounded-[10px] border border-stone-300 py-3 text-black text-sm font-semibold mb-[40px]"
                    onClick={function (): void {}}
                >
                    정보 수정
                </button>
                <Divider />
                {/* portfolio title section */}
                <div className="my-[40px]">
                    <Title text="포트폴리오 제목" />
                    <TextInput
                        value={title}
                        onChange={onChangeTitle}
                        placeholder="기억될만한 제목을 만들어보세요."
                    />
                </div>
                <Divider />
                {/* project section */}
                <section>
                    <div className="my-[40px]">
                        <Title text="프로젝트" />
                        <div className="flex flex-col gap-[10px]">
                            {/* TODO: replace any type to CreateProjectDtoType */}
                            {projects.map((project) => {
                                return (
                                    <Project
                                        key={project.title}
                                        title={project.title}
                                        category={project.content}
                                        startDate={project.startDate}
                                        endDate={project.endDate}
                                    />
                                );
                            })}
                            {isAddingPeoject && (
                                <ProejctEdit
                                    onAddProject={handleOnAddProject}
                                />
                            )}
                            <PlusButton onClick={addEmptyProjectEdit} />
                        </div>
                    </div>
                </section>
                <Divider />
                {/* color selection section */}
                <section>
                    <div className="my-[40px]">
                        <Title text="컬러 선택" />
                        <div className="bg-white rounded-[10px] shadow-md picker-container">
                            <CompactPicker
                                className="w-full"
                                onChangeComplete={handleColorChange}
                            />
                        </div>
                    </div>
                </section>
                <Divider />
                {/* 썸네일 추가 */}
                <section>
                    <div className="my-[40px]">
                        <Title text="썸네일 추가" isRequired={false} />
                        <div className="flex flex-col gap-2 mb-2">
                            <FileInput
                                label={'이미지'}
                                onChange={handleOnChangeFileInput}
                            />
                        </div>
                    </div>
                </section>
                <Divider />
                {/* 공개여부 */}
                <section>
                    <div className="my-[40px]">
                        <Title text="공개 여부" />
                        <div className="flex rounded-[10px] border border-gray-200 py-4 px-[14px] justify-between">
                            <span>비공개</span>
                            <IOSSwitch
                                toggle={() => setIsPublic((prev) => !prev)}
                            />
                        </div>
                    </div>
                </section>
            </div>
            <CreateNavigation
                onCancel={function (): void {
                    navigate(-1);
                }}
                onSave={handleOnSave}
            />
        </>
    );
};

export default PortfolioCreate;

export const Title = ({
    text,
    isRequired = true
}: {
    text: string;
    isRequired?: boolean;
}) => {
    return (
        <div className="flex gap-1 pb-4">
            <h2 className="text-xl font-bold text-black">{text}</h2>
            {isRequired && (
                <span className="text-xl font-bold text-red-600">*</span>
            )}
        </div>
    );
};

const Desc = ({
    label,
    value,
    isRequired = false
}: {
    label: string;
    value: string;
    isRequired?: boolean;
}) => {
    return (
        <div className="flex">
            <span className="text-sm font-normal text-neutral-500 w-[75px]">
                {label}
                {isRequired && (
                    <span className="text-sm font-normal text-red-600">*</span>
                )}
            </span>
            <span className="text-sm font-normal text-black">{value}</span>
        </div>
    );
};
