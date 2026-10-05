--liquibase formatted sql

--changeset bek:1
CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

--changeset bek:2
CREATE TABLE categories(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

--changeset bek:3
CREATE TABLE tasks(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    done BOOLEAN,
    date DATE,
    time TIME,
    status VARCHAR(20),
    category_id BIGINT,
    user_id BIGINT,
    CONSTRAINT fk_tasks_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT fk_tasks_user FOREIGN KEY (user_id) REFERENCES users(id)
);
