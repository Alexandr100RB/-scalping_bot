CREATE TABLE preferences (
    id BIGSERIAL PRIMARY KEY,
    tinkoff_token VARCHAR(255),
    telegram_bot_name VARCHAR(255),
    telegram_bot_token VARCHAR(255)
);

COMMENT ON TABLE preferences IS 'Таблица для хранения токенов песочниц и тг бота';

COMMENT ON COLUMN preferences.id IS 'Id записи';
COMMENT ON COLUMN preferences.tinkoff_token IS 'chatId пользователя';
COMMENT ON COLUMN preferences.telegram_bot_name IS 'Название бота';
COMMENT ON COLUMN preferences.telegram_bot_token IS 'Токен бота';