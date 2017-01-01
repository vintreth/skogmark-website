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
  (0, NOW(), '�', 2),
  (0, NOW(), '�����', 2),
  (0, NOW(), '����', 2),
  (0, NOW(), '���', 2),
  (0, NOW(), '�', 2),
  (0, NOW(), '����', 2),
  (0, NOW(), '� ��', 2),
  (0, NOW(), '��', 2),
  (0, NOW(), '��� ��', 2),
  (0, NOW(), '������', 2),
  (0, NOW(), '�� ����', 2),
  (0, NOW(), '� ������', 2),
  (0, NOW(), '���', 3),
  (0, NOW(), '�����', 3),
  (0, NOW(), '���', 3),
  (0, NOW(), '�����', 3),
  (0, NOW(), '���', 3),
  (0, NOW(), '����', 3),
  (0, NOW(), '������', 3),
  (0, NOW(), '��� �����', 3),
  (0, NOW(), '��� ��', 3),
  (0, NOW(), '�������', 3),
  (0, NOW(), '������ ���', 3),
  (0, NOW(), '��� ���', 3),
  (0, NOW(), '����', 3),
  (0, NOW(), '����', 3),
  (0, NOW(), '��� ���', 3);