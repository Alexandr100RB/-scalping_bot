package kosyaninchuyko.tgscalping.telegram.bot.command;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Реджистри бинов команд телеграм бота
 *
 * @since 24.03.2024
 */
public class CommandRegistry {
    public static final Map<TelegramCommand, UserCommand> TG_COMMANDS = new EnumMap<>(TelegramCommand.class);

    private CommandRegistry() {
    }
}
