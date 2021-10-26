CREATE TABLE IF NOT EXISTS t_user_role
(
    id SERIAL PRIMARY KEY NOT NULL,
    t_user_id LONG NOT NULL,
    t_role_id LONG NOT NULL,
);