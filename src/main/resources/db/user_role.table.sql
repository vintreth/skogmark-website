drop TABLE IF EXISTS s_user_role;
CREATE TABLE s_user_role(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(16) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;