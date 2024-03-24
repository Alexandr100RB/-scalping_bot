package kosyaninchuyko.tgscalping.telegram.bot.command;

import javax.annotation.Nonnull;

/**
 * Интерфейс для определения команд телеграм бота
 *
 * @since 24.03.2024
 */
@FunctionalInterface
public interface UserCommand {
    void execute(long chatId, @Nonnull String message);
}
