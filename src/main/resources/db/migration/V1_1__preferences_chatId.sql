ALTER TABLE preferences ADD COLUMN chat_id INTEGER;
ALTER TABLE preferences ALTER COLUMN tinkoff_token SET NOT NULL;
ALTER TABLE preferences ALTER COLUMN telegram_bot_name SET NOT NULL;
ALTER TABLE preferences ALTER COLUMN telegram_bot_token SET NOT NULL;