DROP TABLE IF EXISTS conjunction;

CREATE TABLE conjunction (
  id           BIGSERIAL PRIMARY KEY,
  creator_id   BIGINT,
  date_created TIMESTAMP,
  content      VARCHAR(32) NOT NULL,
  type         INTEGER     NOT NULL
);

INSERT INTO conjunction (creator_id, date_created, content, type) VALUES
 (0, NOW(), '-', 0)
,(0, NOW(), '- это', 0)
,(0, NOW(), 'и', 0)
,(0, NOW(), 'также', 0)
,(0, NOW(), 'тоже', 0)
,(0, NOW(), 'или', 0)
,(0, NOW(), 'а', 0)
,(0, NOW(), 'зато', 0)
,(0, NOW(), 'а то', 0)
,(0, NOW(), 'но', 0)
,(0, NOW(), 'все же', 0)
,(0, NOW(), 'однако', 0)
,(0, NOW(), 'то есть', 0)
,(0, NOW(), 'а именно', 0)
,(0, NOW(), 'что', 1)
,(0, NOW(), 'чтобы', 1)
,(0, NOW(), 'как', 1)
,(0, NOW(), 'когда', 1)
,(0, NOW(), 'где', 1)
,(0, NOW(), 'куда', 1)
,(0, NOW(), 'откуда', 1)
,(0, NOW(), 'как-будто', 1)
,(0, NOW(), 'как бы', 1)
,(0, NOW(), 'столько', 1)
,(0, NOW(), 'потому что', 1)
,(0, NOW(), 'посему', 1)
,(0, NOW(), 'так как', 1)
,(0, NOW(), 'если', 1)
,(0, NOW(), 'хотя', 1)
,(0, NOW(), 'так что', 1)
,(0, NOW(), ',', 2)
,(0, NOW(), ' ', 3)
;