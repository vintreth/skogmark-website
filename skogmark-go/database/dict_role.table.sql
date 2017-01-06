USE `go`;
DROP TABLE IF EXISTS dict_role;
CREATE TABLE dict_role (
  id               INT          NOT NULL,
  creator_id       INT          NOT NULL,
  date_created     DATETIME     NOT NULL,
  code             VARCHAR(32)  NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

INSERT INTO dict_role (id, creator_id, date_created, code) VALUES
  (1, 0, NOW(), 'none'),
  (2, 0, NOW(), 'complex'),
  (3, 0, NOW(), 'compound'),
  (4, 0, NOW(), 'subject'),
  (5, 0, NOW(), 'predicate'),
  (6, 0, NOW(), 'object'),
  (7, 0, NOW(), 'adverbial'),
  (8, 0, NOW(), 'modifier');
