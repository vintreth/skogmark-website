CREATE TABLE IF NOT EXISTS sentence_rating (
    id BIGSERIAL PRIMARY KEY,
    history_id BIGINT NOT NULL,
    chat_id BIGINT NOT NULL,
    vote SMALLINT NOT NULL,
    user_id BIGINT NOT NULL
);