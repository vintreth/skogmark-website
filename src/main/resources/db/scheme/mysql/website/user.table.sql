USE skogmark;
DROP TABLE IF EXISTS s_user;
CREATE TABLE s_user (
  id            INT AUTO_INCREMENT NOT NULL,
  email         VARCHAR(64)        NOT NULL,
  password      VARCHAR(255)       NOT NULL,
  first_name    VARCHAR(64),
  last_name     VARCHAR(64),
  birthday      DATETIME,
  gender        CHAR(1),
  location      VARCHAR(64),
  registered_at DATETIME           NOT NULL,
  active        CHAR(1)            NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;