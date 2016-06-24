DROP TABLE IF EXISTS s_gig;
CREATE TABLE s_gig (
  id         INT          NOT NULL AUTO_INCREMENT,
  created_by INT          NOT NULL,
  created_at DATETIME     NOT NULL,
  start_date DATETIME     NOT NULL,
  stage      VARCHAR(128) NOT NULL,
  location   VARCHAR(128) NOT NULL,
  link       VARCHAR(255) NOT NULL,
  status_id  INT          NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;