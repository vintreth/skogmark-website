DROP TABLE IF EXISTS sk_post;
CREATE TABLE sk_post (
  id           INT AUTO_INCREMENT NOT NULL,
  title        VARCHAR(128)       NOT NULL,
  created_at   DATETIME           NOT NULL,
  created_by   INT                NOT NULL,
  content      TEXT               NOT NULL,
  preview_text TEXT,
  image_id     INT,
  shows        INT DEFAULT 0,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

INSERT INTO sk_post (title, created_at, created_by, content, preview_text, image_id)
  VALUES (
    "А тем временем нас 666!",
    NOW(),
    0,
    "А тем временем нас 666!Магическое число кому доброе кому наоборот.Хотим сообщить вам что мы готовимся и набираем форму для того чтобы играть еще злее!И совсем скоро мы вновь выйдем на сцену.А первым двум написавшим вокалист по случаю 666рок сделает мерч как у него из ваших футболок!!!предложение ограничено!Писать вот сюда<br/><a href=\"https://vk.com/grafhate\">https://vk.com/grafhate</a>",
    "Магическое число кому доброе кому наоборот.Хотим сообщить вам что мы готовимся и набираем форму...",
    1
  );