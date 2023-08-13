DELETE
FROM PORTFOLIO_BOOKMARKS;
DELETE
FROM PORTFOLIO_REVIEWS;
DELETE
FROM PORTFOLIO_TAGS;


DELETE
FROM PROJECT_FILES;
DELETE
FROM PROJECT_STUDY_HISTORIES;


DELETE
FROM STUDY_HISTORY_FILE;
DELETE
FROM STUDY_HISTORY_TAGS;


DELETE
FROM TAGS;
DELETE
FROM FILES;

DELETE
FROM STUDY_HISTORIES;
DELETE
FROM PROJECTS;
DELETE
FROM PORTFOLIOS;
DELETE
FROM MEMBERS;


INSERT INTO MEMBERS(MEMBER_ID, MEMBER_NAME)
values (1, 'memberA'),
       (2, 'memberB'),
       (3, 'memberC');


INSERT INTO TAGS(TAG_ID, NAME)
VALUES (1, '개발'),
       (2, '자바'),
       (3, '스프링'),
       (4, '리액트'),
       (5, '백엔드'),
       (6, '프론트엔드');


INSERT INTO FILES (FILE_ID, FILE_TYPE, FILE_ORIGINAL_NAME, FILE_SAVED_NAME, FILE_URL)
VALUES (1, 'normal', 'memberAFile.zip', 'userId1/memberAFile.zip',
        'remote/userId1/memberAFile.zip'),
       (2, 'timelapse', 'memberATimelapse.mp4', 'userId1/memberATimelapse.mp4',
        'remote/userId1/memberATimelapse.mp4'),
       (3, 'normal', 'memberBFile.zip', 'userId1/memberBFile.zip',
        'remote/userId1/memberBFile.zip'),
       (4, 'normal', 'memberCFile.zip', 'userId1/memberCFile.zip',
        'remote/userId1/memberCFile.zip');


INSERT INTO PORTFOLIOS(PORTFOLIO_ID, PORTFOLIO_MEMBER_ID, PORTFOLIO_IS_PUBLIC, PORTFOLIO_TITLE,
                       PORTFOLIO_CATEGORY, PORTFOLIO_DESCRIPTION, create_at, update_at)
VALUES (1, 1, true, 'portfolio of A 1', '개발 || 백엔드 || 스프링', '스프링 고수가 되보자', '2023-01-01 17:00',
        '2023-01-01 17:00'),
       (2, 1, true, 'portfolio of A 2', '개발 || 백엔드 || 스프링', '스프링 중수가 되보자', '2023-02-01 17:00',
        '2023-02-01 17:00'),
       (3, 1, true, 'portfolio of A 3', '개발 || 프론트엔드 || 리액트', '리액트 고수가 되보자', '2023-03-01 17:00',
        '2023-03-01 17:00'),
       (4, 1, false, 'portfolio of A 4', '개발 || 프론트엔드 || Vue', 'Vue 고수가 되보자', '2023-05-01 18:00',
        '2023-05-01 17:00');



INSERT INTO PORTFOLIO_TAGS (PT_PORTFOLIO_ID, PT_TAG_ID)
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


INSERT INTO PORTFOLIO_BOOKMARKS(PB_MEMBER_ID, PB_PORTFOLIO_ID)
VALUES (2, 1),
       (3, 1);


INSERT INTO PORTFOLIO_REVIEWS(PR_PORTFOLIO_ID, PR_MEMBER_ID, PR_DIFFERENCE_SCORE,
                              PR_EXPERTISE_SCORE, PR_PERFECTION_SCORE,
                              PR_COMMENT)
VALUES (1, 2, 4.0, 3.5, 4.5, '재밌게 잘 봤읍니다'),
       (1, 3, 3.0, 3.0, 3.0, '그저 그랬음');


INSERT INTO PROJECTS (PROJECT_ID, PROJECT_PORTFOLIO_ID, PROJECT_START_DATE, PROJECT_END_DATE,
                      PROJECT_CATEGORY, PROJECT_DESIGN_BACKGROUND, PROJECT_ROLE, PROJECT_TITLE,
                      PROJECT_DESCRIPTION)
VALUES (1, 1, '2023-08-10 12:00', '2023-08-12 12:00', '스프링', '#eeeeee', '팀장', '스프링 부수기',
        '스프링을 부수는 프로젝트'),
       (2, 1, '2023-08-14 12:00', '2023-08-16 12:00', 'JPA', '#ffffff', '팀원', 'JPA 부수기',
        'JPA를 부수는 프로젝트');


INSERT INTO PROJECT_FILES(PF_FILE_ID, PF_PROJECT_ID)
VALUES (1, 1);


INSERT INTO STUDY_HISTORIES (STUDY_HISTORY_ID, STUDY_HISTORY_MEMBER_ID, STUDY_HISTORY_START_DATE,
                             STUDY_HISTORY_END_DATE, STUDY_HISTORY_IS_OPEN, STUDY_HISTORY_TYPE,
                             STUDY_HISTORY_CATEGORY, STUDY_HISTORY_CONTENT, create_at, update_at)
VALUES (1, 1, '2023-08-11 13:00', '2023-08-11 14:00', true, '스프링', '개발 || 백엔드 || 스프링', '스프링 DI 박살',
        '2023-01-01 17:00',
        '2023-01-01 17:00'),
       (2, 1, '2023-08-12 13:00', '2023-08-12 14:00', true, '스프링', '개발 || 백엔드 || 스프링', '자바 스트림 박살',
        '2023-02-01 17:00',
        '2023-02-01 17:00'),
       (3, 1, '2023-09-12 13:00', '2023-08-12 14:00', true, '영어', '영어 || 토익', '토익 공부',
        '2023-03-01 17:00',
        '2023-03-01 17:00');


INSERT INTO STUDY_HISTORY_TAGS(SHT_STUDY_HISTORY_ID, SHT_TAG_ID)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 5),
       (2, 1),
       (2, 2);


INSERT INTO STUDY_HISTORY_FILE(SHF_STUDY_HISTORY_ID, SHF_FILE_ID)
VALUES (1, 1),
       (1, 2);


INSERT INTO PROJECT_STUDY_HISTORIES(PSH_PROJECT_ID, PSH_STUDY_HISTORY_ID)
VALUES (1, 1),
       (1, 2);
