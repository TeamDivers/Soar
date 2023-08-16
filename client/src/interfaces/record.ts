export interface RecordType {
    id: number;
    content: string;
    category: string;
    tagName: string;
    startDate: string;
    endDate: string;
    memberId: number;
    timelapseURL: string;
    files: string[];
    public: boolean;
}
