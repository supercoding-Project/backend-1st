CREATE TABLE comment (
     comment_id INT PRIMARY KEY AUTO_INCREMENT,
     content VARCHAR(255) NOT NULL,
     user_id INT NOT NULL,
     post_id INT NOT NULL,
     createdAt DATETIME NOT NULL
);