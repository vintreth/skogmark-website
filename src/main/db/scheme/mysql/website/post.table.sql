DROP TABLE IF EXISTS skogmark_post;
CREATE TABLE skogmark_post (
  id            INT AUTO_INCREMENT,
  title         VARCHAR(128) NOT NULL,
  created_at    DATETIME     NOT NULL,
  created_by    INT          NOT NULL,
  content       TEXT         NOT NULL,
  preview_text  TEXT,
  preview_image INT,
  shows         INT DEFAULT 0,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;