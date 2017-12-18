DROP TABLE IF EXISTS sentence;

CREATE TABLE sentence (
  id           BIGSERIAL PRIMARY KEY,
  creator_id   BIGINT,
  date_created TIMESTAMP WITH TIME ZONE,
  content      VARCHAR(255) NOT NULL,
  role         INTEGER
);