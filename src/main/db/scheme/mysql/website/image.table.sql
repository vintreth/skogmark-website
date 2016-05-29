DROP TABLE IF EXISTS s_image;
CREATE TABLE s_image (
  id         INT AUTO_INCREMENT NOT NULL,
  created_at DATETIME           NOT NULL,
  name       VARCHAR(255)       NOT NULL,
  path       VARCHAR(255)       NOT NULL,
  mime_type  VARCHAR(32)        NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

INSERT INTO s_image (created_at, name, path, mime_type) VALUES (
    NOW(),
    "yQiHPnMF--I.jpg",
    "2ba",
    "image/jpeg"
);