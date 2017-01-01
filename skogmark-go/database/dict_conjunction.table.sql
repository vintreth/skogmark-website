DROP TABLE IF EXISTS dict_conjunction;
CREATE TABLE dict_conjunction (
  id               INT          NOT NULL AUTO_INCREMENT,
  creator_id       INT          NOT NULL,
  date_created     DATETIME     NOT NULL,
  content          VARCHAR(32)  NOT NULL,
  role_id          INT          NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

INSERT INTO dict_conjunction (creator_id, date_created, content, role_id) VALUES
  (0, NOW(), '-', 2),
  (0, NOW(), 'и', 2),
  (0, NOW(), 'также', 2),
  (0, NOW(), 'тоже', 2),
  (0, NOW(), 'или', 2),
  (0, NOW(), 'а', 2),
  (0, NOW(), 'зато', 2),
  (0, NOW(), 'а то', 2),
  (0, NOW(), 'но', 2),
  (0, NOW(), 'все же', 2),
  (0, NOW(), 'однако', 2),
  (0, NOW(), 'то есть', 2),
  (0, NOW(), 'а именно', 2),
  (0, NOW(), 'что', 3),
  (0, NOW(), 'чтобы', 3),
  (0, NOW(), 'как', 3),
  (0, NOW(), 'когда', 3),
  (0, NOW(), 'где', 3),
  (0, NOW(), 'куда', 3),
  (0, NOW(), 'откуда', 3),
  (0, NOW(), 'как будто', 3),
  (0, NOW(), 'как бы', 3),
  (0, NOW(), 'столько', 3),
  (0, NOW(), 'потому что', 3),
  (0, NOW(), 'так как', 3),
  (0, NOW(), 'если', 3),
  (0, NOW(), 'хотя', 3),
  (0, NOW(), 'так что', 3);