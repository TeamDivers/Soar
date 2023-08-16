DELETE
FROM portfolio_bookmarks;
DELETE
FROM portfolio_reviews;
DELETE
FROM portfolio_tags;


DELETE
FROM project_files;
DELETE
FROM project_study_histories;


DELETE
FROM study_history_file;
DELETE
FROM study_history_tags;


DELETE
FROM tags;
DELETE
FROM files;

DELETE
FROM study_histories;
DELETE
FROM projects;
DELETE
FROM portfolios;
DELETE
FROM members;


INSERT INTO members(MEMBER_ID, MEMBER_NAME, MEMBER_EMAIL)
values (1, 'memberA', 'aaaa@gmail.com'),
       (2, 'memberB', 'aaaa@gmail.com'),
       (3, 'memberC', 'aaaa@gmail.com'),
       (4, '김길동', 'aaaa@gmail.com'),
       (5, '김재민', 'aaaa@gmail.com'),
       (6, '김진호', 'aaaa@gmail.com'),
       (7, '김member', 'aaaa@gmail.com');


INSERT INTO tags(TAG_ID, NAME)
VALUES (1, '개발'),
       (2, '자바'),
       (3, '스프링'),
       (4, '리액트'),
       (5, '백엔드'),
       (6, '프론트엔드');


INSERT INTO files (FILE_ID, FILE_TYPE, FILE_ORIGINAL_NAME, FILE_SAVED_NAME, FILE_URL)
VALUES (1, 'normal', 'memberAFile.zip', 'userId1/memberAFile.zip',
        'remote/userId1/memberAFile.zip'),
       (2, 'timelapse', 'memberATimelapse.mp4', 'userId1/memberATimelapse.mp4',
        'remote/userId1/memberATimelapse.mp4'),
       (3, 'normal', 'memberBFile.zip', 'userId1/memberBFile.zip',
        'remote/userId1/memberBFile.zip'),
       (4, 'normal', 'memberCFile.zip', 'userId1/memberCFile.zip',
        'remote/userId1/memberCFile.zip');


INSERT INTO portfolios(PORTFOLIO_ID, PORTFOLIO_MEMBER_ID, PORTFOLIO_IS_PUBLIC, PORTFOLIO_TITLE,
                       PORTFOLIO_CATEGORY, PORTFOLIO_DESCRIPTION, create_at, update_at)
VALUES (1, 1, true, 'portfolio of A 1', '개발 || 백엔드 || 스프링', '스프링 고수가 되보자', '2023-01-01 17:00',
        '2023-01-01 17:00'),
       (2, 1, true, 'portfolio of A 2', '개발 || 백엔드 || 스프링', '스프링 중수가 되보자', '2023-02-01 17:00',
        '2023-02-01 17:00'),
       (3, 1, true, 'portfolio of A 3', '개발 || 프론트엔드 || 리액트', '리액트 고수가 되보자', '2023-03-01 17:00',
        '2023-03-01 17:00'),
       (4, 1, false, 'portfolio of A 4', '개발 || 프론트엔드 || Vue', 'Vue 고수가 되보자', '2023-05-01 18:00',
        '2023-05-01 17:00');



INSERT INTO portfolio_tags (PT_PORTFOLIO_ID, PT_TAG_ID)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 5),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 5),
       (3, 1),
       (3, 4),
       (3, 6);


INSERT INTO portfolio_bookmarks(PB_MEMBER_ID, PB_PORTFOLIO_ID)
VALUES (2, 1),
       (3, 1);


INSERT INTO portfolio_reviews(PR_PORTFOLIO_ID, PR_MEMBER_ID, PR_DIFFERENCE_SCORE,
                              PR_EXPERTISE_SCORE, PR_PERFECTION_SCORE,
                              PR_COMMENT)
VALUES (1, 2, 4.0, 3.5, 4.5, '재밌게 잘 봤읍니다'),
       (1, 3, 3.0, 3.0, 3.0, '그저 그랬음');


INSERT INTO projects (PROJECT_ID, PROJECT_PORTFOLIO_ID, PROJECT_START_DATE, PROJECT_END_DATE,
                      PROJECT_CATEGORY, PROJECT_DESIGN_BACKGROUND, PROJECT_ROLE, PROJECT_TITLE,
                      PROJECT_DESCRIPTION)
VALUES (1, 1, '2023-08-10 12:00', '2023-08-12 12:00', '스프링', '#eeeeee', '팀장', '스프링 부수기',
        '스프링을 부수는 프로젝트'),
       (2, 1, '2023-08-14 12:00', '2023-08-16 12:00', 'JPA', '#ffffff', '팀원', 'JPA 부수기',
        'JPA를 부수는 프로젝트');


INSERT INTO project_files(PF_FILE_ID, PF_PROJECT_ID)
VALUES (1, 1);


INSERT INTO study_histories (STUDY_HISTORY_ID, STUDY_HISTORY_MEMBER_ID, STUDY_HISTORY_START_DATE,
                             STUDY_HISTORY_END_DATE, STUDY_HISTORY_IS_OPEN,
                             STUDY_HISTORY_CATEGORY, STUDY_HISTORY_CONTENT, create_at, update_at)
VALUES (1, 1, '2023-08-11 13:00', '2023-08-11 14:00', true, '개발 || 백엔드 || 스프링', '스프링 DI 박살',
        '2023-01-01 17:00',
        '2023-01-01 17:00'),
       (2, 1, '2023-08-12 13:00', '2023-08-12 14:00', true, '개발 || 백엔드 || 스프링', '자바 스트림 박살',
        '2023-02-01 17:00',
        '2023-02-01 17:00'),
       (3, 1, '2023-09-12 13:00', '2023-08-12 14:00', true, '영어 || 토익', '토익 공부',
        '2023-03-01 17:00',
        '2023-03-01 17:00');


INSERT INTO study_history_tags(SHT_STUDY_HISTORY_ID, SHT_TAG_ID)
VALUES (1, 5),
       (2, 1);



INSERT INTO study_history_file(SHF_STUDY_HISTORY_ID, SHF_FILE_ID)
VALUES (1, 1),
       (1, 2);


INSERT INTO project_study_histories(PSH_PROJECT_ID, PSH_STUDY_HISTORY_ID)
VALUES (1, 1),
       (1, 2);