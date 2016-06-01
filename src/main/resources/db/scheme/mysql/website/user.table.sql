USE skogmark;
DROP TABLE IF EXISTS s_user;
CREATE TABLE s_user (
  id            INT AUTO_INCREMENT NOT NULL,
  email         VARCHAR(255)       NOT NULL,
  registered_at DATETIME,
  active        TINYINT(1)         NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;