CREATE TABLE likes (
                       likes_id INT PRIMARY KEY AUTO_INCREMENT,
                       user_id INT NOT NULL,
                       comment_id INT NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                       FOREIGN KEY (comment_id) REFERENCES comment(comment_id) ON DELETE CASCADE
);