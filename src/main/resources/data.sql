INSERT Into Article (title, content,user_Id) values('가가가가','1111',2);
INSERT Into Article (title, content,user_Id) values('가가가가','2222',2);
INSERT Into Article (title, content,user_Id) values('가가가가','3333',2);

INSERT Into MEMBER_ENTITY (email, name, password) values('1@1','ajv','dfgb');
INSERT Into MEMBER_ENTITY (email, name, password) values('2@2','zcxv','badf');
INSERT Into MEMBER_ENTITY  (email, name, password) values('3@3','trew','bgf');

INSERT INTO article( title, content,user_Id) VALUES( '가가가가', '1111',2);
INSERT INTO article( title, content,user_Id) VALUES( '나나나나', '2222',2);
INSERT INTO article( title, content,user_Id) VALUES( '다다다다', '3333',2);

-- article 테이블에 데이터 추가
INSERT INTO article(title, content,user_Id) VALUES('당신의 인생 영화는?', '댓글 고',2);
INSERT INTO article(title, content,user_Id) VALUES('당신의 소울 푸드는?', '댓글 고고',2);
INSERT INTO article(title, content,user_Id) VALUES('당신의 취미는?', '댓글 고고고',2);

-- 4번 게시글의 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Park', '굿 윌 헌팅');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Kim', '아이 엠 샘');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Choi', '쇼생크 탈출');

-- 5번 게시글의 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Park', '치킨');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Kim', '샤브샤브');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Choi', '초밥');

-- 6번 게시글의 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Park', '조깅');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Kim', '유튜브 시청');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Choi', '독서');

Insert into notice(title, body) values('공지','공지');