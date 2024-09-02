-- Создание таблицы авторов
CREATE TABLE author (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date_of_birth DATE
);

-- Создание таблицы жанров
CREATE TABLE genres (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Создание таблицы издателей
CREATE TABLE publisher (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Создание таблицы книг
CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    content BYTEA, -- Хранение PDF документов
    page_count INT,
    isbn VARCHAR(20), -- ISBN лучше хранить как строку
    genre_id BIGINT,
    author_id BIGINT,
    publish_year INT,
    publisher_id BIGINT,
    image BYTEA, -- Хранение JPEG изображений
    avg_rating DOUBLE PRECISION, -- Используйте DOUBLE PRECISION для чисел с плавающей запятой
    total_vote_count INT,
    total_rating INT,
    view_count INT,
    description TEXT,
    FOREIGN KEY (genre_id) REFERENCES genres(id),
    FOREIGN KEY (author_id) REFERENCES author(id),
    FOREIGN KEY (publisher_id) REFERENCES publisher(id)
);

-- Создание таблицы пользователей
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL -- Используйте ENUM или VARCHAR в зависимости от поддержки вашей СУБД
);

-- Создание таблицы голосований
CREATE TABLE vote (
    id BIGSERIAL PRIMARY KEY,
    value INT,
    book_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);