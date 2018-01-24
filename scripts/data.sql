DROP DATABASE IF EXISTS `vv`;
CREATE DATABASE `vv`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;
;
USE `vv`;

-- 用户
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  id              BIGINT NOT NULL AUTO_INCREMENT,
  username        VARCHAR(100) COMMENT 'username',
  password        VARCHAR(100),
  salt            VARCHAR(20),
  email           VARCHAR(100),
  createTime      DATETIME DEFAULT NOW(6),
  CONSTRAINT pk_user PRIMARY KEY (id)
) ENGINE = InnoDB CHARSET = utf8;

CREATE UNIQUE INDEX idx_user_username
  ON `user` (username);

INSERT INTO `user`
(id,username,PASSWORD,salt,email,createTime)
VALUES
(1,'vv','123','s','e',SYSDATE(6));

INSERT INTO `user`
(id,username,PASSWORD,salt,email,createTime)
VALUES
(2,'vv2','123','s','e'),
(3,'vv3','123','s','e');



-- SELECT id,username,PASSWORD,salt,email,createTime FROM USER;