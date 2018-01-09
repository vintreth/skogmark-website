CREATE TABLE IF NOT EXISTS sentence_history (
    id BIGSERIAL PRIMARY KEY,
    creator_id   BIGINT,
    date_created TIMESTAMP,
    content VARCHAR(255) NOT NULL
);