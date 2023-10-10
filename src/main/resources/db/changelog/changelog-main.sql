-- liquibase formatted sql

-- changelog csa21472001:1
CREATE TABLE task_notifications (
id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
chat_id BIGINT NOT NULL,
message VARCHAR(255) NOT NULL,
notification_date_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,

CONSTRAINT "task_notificationsPK" PRIMARY KEY (id));
