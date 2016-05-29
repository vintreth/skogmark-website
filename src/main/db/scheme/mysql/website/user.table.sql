USE skogmark;
DROP TABLE IF EXISTS sk_user;
CREATE TABLE sk_user (
  id            INT AUTO_INCREMENT NOT NULL,
  email         VARCHAR(255)       NOT NULL,
  registered_at DATETIME,
  active        TINYINT(1)         NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;