DROP TABLE IF EXISTS s_status;
CREATE TABLE s_status(
  id INT NOT NULL AUTO_INCREMENT,
  value VARCHAR(16) NOT NULL,
  color VARCHAR(6) DEFAULT "000000",
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

INSERT INTO s_status (value, color) VALUES
  ("confirmed", "00A65A"),
  ("not confirmed", "F39C12"),
  ("canceled", "DD4B39");