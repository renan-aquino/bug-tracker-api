CREATE TABLE USERS (
    ID TEXT PRIMARY KEY UNIQUE NOT NULL,
    LOGIN TEXT NOT NULL UNIQUE,
    PASSWORD TEXT NOT NULL,
    ROLE TEXT NOT NULL
)