interface CreatePortfolioDto {
    portfolio: {
        title: string;
        description: string;
        category: string;
        isPublic: boolean;
        memberId: number;
        tags: string[];
        background: string;
    };
    projectList: {
        title: string;
        category: string;
        role: string;
        description: string;
        startDate: string;
        endDate: string;
        histories: number[];
    }[];
    files: File[];
    thumbnail: File;
}

interface PortfolioType {
    portfolioId: number;
    memberId: number;
    title: string;
    description: string;
    category: string;
    bookmark: number;
    rank: number;
    score: number;
    projects: Project[];
    reviews: Review[];
    tags: Tags;
}

interface Tags {
    name: string[];
}

interface Review {
    memberId: number;
    portfolioId: number;
    expertiseScore: number;
    differenceScore: number;
    perfectionScore: number;
    comment: string;
}

interface Project {
    projectId: number;
    portfolioId: number;
    title: string;
    category: string;
    role: string;
    description: string;
    startDate: string;
    endDate: string;
    studyHistories: StudyHistory[];
    files: File[];
}

// interface File {
//     fileId: number;
//     type: string;
//     fileName: string;
//     url: string;
// }

interface StudyHistory {
    id: number;
    content: string;
    memberId: number;
    tagName: string;
    startDate: string;
    endDate: string;
}

export type {
    CreatePortfolioDto,
    PortfolioType,
    Tags,
    Review,
    Project,
    StudyHistory
};
