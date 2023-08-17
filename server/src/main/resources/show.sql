insert into members (member_id, member_name, phone_number, education, career, member_email)
values (1, '전진호', '010-1234-5678', '대학생 / 건국대학교', 'WIT 2기 졸업 외 6개', 'jino@gmail.com'),
       (2, '전호찬', '010-1234-5678', '취업준비생 / 무직', '2023년 건국대학교 졸업 외 2개', 'hochan@gmail.com'),
       (3, '전재민', '010-1234-5678', '고등학생 / 건국고등학교', '2023년 건국해커톤 우승 외 3개', 'jaemin@gmail.com'),
       (4, '나진욱', '010-1234-5678', '직장인', '건국전자 OO팀 팀장 외 5개', 'jinook@gmail.com'),
       (5, '김민지', '010-1234-5678', '학생', 'WIT 2기 졸업 외 6개', 'minji@gmail.com'),
       (6, '김나은', '010-1234-5678', '영어강사', '건국 영어스쿨 교사 외 6개', 'naeun@gmail.com');

insert into study_histories (study_history_id, study_history_member_id,
                             study_history_is_open,
                             study_history_start_date, study_history_end_date,
                             study_history_category, study_history_content)
values (1, 1, true,
        '2023-08-08 00:00', '2023-08-08 01:00',
        '수학',
        '새로운 풀이과정을 도입한 확통 24,35,36번 풀이.짜-x,
짬-y, 탕-z 대입'),
       (2, 1, true,
        '2023-08-09 00:00', '2023-08-09 01:00',
        '수학',
        '새로운 풀이과정을 도입한 확통 24,35,36번 풀이.짜-x,
짬-y, 탕-z 대입'),
       (3, 1, true,
        '2023-08-08 00:00', '2023-08-08 01:00',
        '국어',
        '새로운 풀이과정을 도입한 확통 24,35,36번 풀이.짜-x,
짬-y, 탕-z 대입'),
       (4, 1, true,
        '2023-08-15 00:00', '2023-08-15 01:00',
        '국어',
        '새로운 풀이과정을 도입한 확통 24,35,36번 풀이.짜-x,
짬-y, 탕-z 대입'),
       (5, 1, true,
        '2023-08-08 00:00', '2023-08-08 01:00',
        '캘리그라피',
        '새로운 풀이과정을 도입한 확통 24,35,36번 풀이.짜-x,
짬-y, 탕-z 대입'),
       (6, 1, true,
        '2023-08-15 00:00', '2023-08-15 01:00',
        '물리',
        '새로운 풀이과정을 도입한 확통 24,35,36번 풀이.짜-x,
짬-y, 탕-z 대입'),
       (7, 1, true,
        '2023-08-20 00:00', '2023-08-20 01:00',
        '물리',
        '새로운 풀이과정을 도입한 확통 24,35,36번 풀이.짜-x,
짬-y, 탕-z 대입'),
       (8, 1, true,
        '2023-08-29 00:00', '2023-08-29 01:00',
        '코딩',
        '새로운 풀이과정을 도입한 확통 24,35,36번 풀이.짜-x,
짬-y, 탕-z 대입');

insert into files (file_id, file_type, file_original_name, file_saved_name, file_url)
values (1, 'png', 'my.png', 'my.png',
        'https://hoosabucket.s3.ap-northeast-2.amazonaws.com/myimg.png'),
       (2, 'png', 'portfolio.png', 'portfolio.png',
        'https://hoosabucket.s3.ap-northeast-2.amazonaws.com/portfolio.png');

insert into study_history_file (shf_file_id, shf_study_history_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8);


insert into tags (tag_id, name)
values (1, '공부태그');
insert into study_history_tags (sht_study_history_id, sht_tag_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1);


insert into portfolios (portfolio_id, portfolio_member_id,
                        create_at, update_at,
                        portfolio_is_public, portfolio_design_background,
                        portfolio_title,
                        portfolio_category,
                        portfolio_description)
values (1, 1,
        '2023-08-12 00:00', '2023-08-12 00:00',
        true, '#E64AF7',
        'ios 개발자 입니다',
        '개발자 포트폴리오',
        '안녕하세요 개발을 사랑..하는 학생 전!! 지노예요~~
안녕하세요 개발을 사랑..하는 학생 전!! 지노예요~~ ...'),
       (2, 1,
        '2023-08-13 00:00', '2023-08-13 00:00',
        true, '#B75721',
        '자바 개발자 입니다',
        '개발자 포트폴리오',
        '안녕하세요 개발을 사랑..하는 학생 전!! 지노예요~~
안녕하세요 개발을 사랑..하는 학생 전!! 지노예요~~ ...'),
       (3, 1,
        '2023-08-13 00:00', '2023-08-13 00:00',
        true, '#4CA2A4',
        '파이썬 개발자 입니다',
        '개발자 포트폴리오',
        '안녕하세요 개발을 사랑..하는 학생 전!! 지노예요~~
안녕하세요 개발을 사랑..하는 학생 전!! 지노예요~~ ...'),
       (4, 1,
        '2023-08-13 00:00', '2023-08-13 00:00',
        true, '#000000',
        'HTML 개발자 입니다',
        '개발자 포트폴리오',
        '안녕하세요 개발을 사랑..하는 학생 전!! 지노예요~~
안녕하세요 개발을 사랑..하는 학생 전!! 지노예요~~ ...');


insert into portfolio_files (prf_file_id, prf_portfolio_id)
values (2, 1),
       (2, 2),
       (2, 3),
       (2, 4);

insert into portfolio_reviews (pr_member_id, pr_portfolio_id,
                               pr_difference_score, pr_expertise_score, pr_perfection_score,
                               pr_comment)
values (2, 1,
        3.5, 3.5, 3.5,
        'comment'),
       (2, 2,
        3.4, 3.4, 3.4,
        'comment'),
       (2, 3,
        3.3, 3.3, 3.3,
        'comment'),
       (2, 4,
        3.2, 3.2, 3.2,
        'comment');

# 진욱 = 4
# new portfolio id = 5
insert into portfolios (portfolio_id, portfolio_member_id,
                        create_at, update_at,
                        portfolio_is_public, portfolio_design_background,
                        portfolio_title,
                        portfolio_category,
                        portfolio_description)
values (5, 4,
        '2023-08-12 00:00', '2023-08-12 00:00',
        true, '#E64AF7',
        'ios 개발자 입니다',
        '개발자 포트폴리오',
        '안녕하세요 개발을 사랑..하는 학생 전!! 진욱이예요~~
   안녕하세요 개발을 사랑..하는 학생 나!! 진욱이예요~~ ...'),
       (6, 5,
        '2023-08-12 00:00', '2023-08-12 00:00',
        true, '#4CA2A4',
        'ios 개발자 입니다',
        '개발자 포트폴리오',
        '안녕하세요 개발을 사랑..하는 학생 전!! 진욱이예요~~
   안녕하세요 개발을 사랑..하는 학생 나!! 진욱이예요~~ ...'),
       (7, 6,
        '2023-08-12 00:00', '2023-08-12 00:00',
        true, '#B75721',
        'ios 개발자 입니다',
        '개발자 포트폴리오',
        '안녕하세요 개발을 사랑..하는 학생 전!! 진욱이예요~~
   안녕하세요 개발을 사랑..하는 학생 나!! 진욱이예요~~ ...'),
       (8, 6,
        '2023-08-12 00:00', '2023-08-12 00:00',
        true, '#B75721',
        'ios 개발자 입니다',
        '개발자 포트폴리오',
        '안녕하세요 개발을 사랑..하는 학생 전!! 진욱이예요~~
안녕하세요 개발을 사랑..하는 학생 나!! 진욱이예요~~ ...');

insert into portfolio_reviews (pr_member_id, pr_portfolio_id,
                               pr_difference_score, pr_expertise_score, pr_perfection_score,
                               pr_comment)
values (2, 5,
        5.0, 5.0, 5.0,
        'comment'),
       (2, 6,
        4.5, 4.5, 4.5,
        'comment'),
       (2, 7,
        4.3, 4.3, 4.3,
        'comment'),
       (2, 8,
        4.1, 4.1, 4.1,
        'comment');

