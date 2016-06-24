USE skogmark;
DROP TABLE IF EXISTS s_tag_post;
CREATE TABLE s_tag_post (
  tag_id  INT NOT NULL,
  post_id INT NOT NULL,
  CONSTRAINT UNIQUE tag_post (tag_id, post_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

INSERT INTO s_tag_post (tag_id, post_id) VALUES
  (1, 1),
  (2, 1),
  (3, 1);