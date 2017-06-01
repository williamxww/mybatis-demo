DROP DATABASE IF EXISTS `shiro`;
CREATE DATABASE `shiro`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;
;
USE `shiro`;


DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
  id              BIGINT AUTO_INCREMENT,
  organization_id BIGINT,
  username        VARCHAR(100),
  password        VARCHAR(100),
  salt            VARCHAR(100),
  role_ids        VARCHAR(100),
  locked          BOOL   DEFAULT FALSE,
  CONSTRAINT pk_sys_user PRIMARY KEY (id)
)
  CHARSET = utf8
  ENGINE = InnoDB;
CREATE UNIQUE INDEX idx_sys_user_username
  ON sys_user (username);
CREATE INDEX idx_sys_user_organization_id
  ON sys_user (organization_id);


DROP TABLE IF EXISTS sys_organization;
CREATE TABLE sys_organization (
  id         BIGINT AUTO_INCREMENT,
  name       VARCHAR(100),
  parent_id  BIGINT,
  parent_ids VARCHAR(100),
  available  BOOL   DEFAULT FALSE,
  CONSTRAINT pk_sys_organization PRIMARY KEY (id)
)
  CHARSET = utf8
  ENGINE = InnoDB;
CREATE INDEX idx_sys_organization_parent_id
  ON sys_organization (parent_id);
CREATE INDEX idx_sys_organization_parent_ids
  ON sys_organization (parent_ids);


DROP TABLE IF EXISTS sys_resource;
CREATE TABLE sys_resource (
  id         BIGINT AUTO_INCREMENT,
  name       VARCHAR(100),
  type       VARCHAR(50),
  url        VARCHAR(200),
  parent_id  BIGINT,
  parent_ids VARCHAR(100),
  permission VARCHAR(100),
  available  BOOL   DEFAULT FALSE,
  CONSTRAINT pk_sys_resource PRIMARY KEY (id)
)
  CHARSET = utf8
  ENGINE = InnoDB;
CREATE INDEX idx_sys_resource_parent_id
  ON sys_resource (parent_id);
CREATE INDEX idx_sys_resource_parent_ids
  ON sys_resource (parent_ids);


DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  id           BIGINT AUTO_INCREMENT,
  role         VARCHAR(100),
  description  VARCHAR(100),
  resource_ids VARCHAR(100),
  available    BOOL   DEFAULT FALSE,
  CONSTRAINT pk_sys_role PRIMARY KEY (id)
)
  CHARSET = utf8
  ENGINE = InnoDB;
CREATE INDEX idx_sys_role_resource_ids
  ON sys_role (resource_ids);


DROP TABLE IF EXISTS sessions;
CREATE TABLE sessions (
  id      VARCHAR(200),
  session VARCHAR(2000),
  CONSTRAINT pk_sessions PRIMARY KEY (id)
)
  CHARSET = utf8
  ENGINE = InnoDB;


DROP TABLE IF EXISTS REPORT_ONU;
CREATE TABLE REPORT_ONU (
  id           VARCHAR(100),
  EMSID        INT,
  NEID         INT,
  boardId      INT,
  portNo       INT,
  onuId        INT,
  onuPonPortNo INT,
  emsName      VARCHAR(300),
  areaName     VARCHAR(300),
  neName       VARCHAR(300),
  neIp         VARCHAR(300),
  boardName    VARCHAR(300),
  onuName      VARCHAR(300),
  pmValue301   DOUBLE,
  pmValue302   DOUBLE,
  pmValue303   DOUBLE,
  pmValue304   DOUBLE,
  pmValue305   DOUBLE
)
  CHARSET = UTF8
  ENGINE = INNODB;

alter table REPORT_ONU add constraint FOR_NEID foreign key(NEID) references REPORT_NE(NEID);


DROP TABLE IF EXISTS REPORT_NE;
CREATE TABLE REPORT_NE (
  id           VARCHAR(100),
  EMSID        INT,
  NEID         INT,
  emsName      VARCHAR(300),
  areaName     VARCHAR(300),
  neName       VARCHAR(300),
  neIp         VARCHAR(300),
  pmValue101   DOUBLE,
  pmValue102   DOUBLE,
  pmValue103   DOUBLE,
  pmValue104   DOUBLE,
  pmValue105   DOUBLE
)
  CHARSET = UTF8
  ENGINE = INNODB;
ALTER TABLE `REPORT_NE` ADD INDEX index_NEID ( `NEID` )

