USE skogmark;
DROP TABLE IF EXISTS s_tag;
CREATE TABLE s_tag (
  id         INT          NOT NULL AUTO_INCREMENT,
  value      VARCHAR(255) NOT NULL,
  created_at DATETIME     NOT NULL,
  created_by INT          NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

CREATE INDEX `value` ON s_tag(`value`);

INSERT INTO s_tag (value, created_at, created_by) VALUES
  ("Sworn To Paganism", NOW(), 0),
  ("New Album", NOW(), 0),
  ("Recording", NOW(), 0);