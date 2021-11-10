CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(160) NOT NULL,
    email VARCHAR(160) NOT NULL,
    password VARCHAR(160) NOT NULL ,
    avatar VARCHAR(160) NOT NULL ,
    role_id int constraint t_user_table_t_role_table_id_fk references t_role

);