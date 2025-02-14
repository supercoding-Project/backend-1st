CREATE TABLE post (
    `post_id` bigint NOT NULL AUTO_INCREMENT,
    `title` varchar(50) NOT NULL,
    `content` varchar(255) NOT NULL,
    `user_id` int NOT NULL,
    `created_at` datetime NOT NULL,
    PRIMARY KEY (`post_id`)
)