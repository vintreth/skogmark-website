DROP TABLE IF EXISTS s_user_role;
CREATE TABLE s_user_role (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  CONSTRAINT UNIQUE user_role (user_id, role_id)
);

INSERT INTO s_user_role (user_id, role_id) VALUES
  (1, 1);