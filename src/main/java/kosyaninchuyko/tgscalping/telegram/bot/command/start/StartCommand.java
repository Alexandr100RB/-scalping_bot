package kosyaninchuyko.tgscalping.telegram.bot.command.start;

import kosyaninchuyko.tgscalping.telegram.bot.TelegramBot;
import kosyaninchuyko.tgscalping.telegram.bot.command.TelegramCommand;
import kosyaninchuyko.tgscalping.telegram.bot.command.UserCommand;

import javax.annotation.Nonnull;

import java.util.Arrays;
import java.util.stream.Stream;

import static kosyaninchuyko.tgscalping.telegram.bot.command.CommandRegistry.TG_COMMANDS;

/**
 * Команда старта телеграм бота
 *
 * @since 24.03.2024
 */
public class StartCommand implements UserCommand {
    private final TelegramBot bot;

    public StartCommand(TelegramBot bot) {
        this.bot = bot;
        TG_COMMANDS.put(TelegramCommand.START, this);
    }

    @Override
    public void execute(long chatId, @Nonnull String message) {
        String answer = "Бот стартанул и работает ";
        bot.sendMessage(chatId, answer);
        String commandAnswer = "Список команд: " + Arrays.stream(TelegramCommand.values()).map(TelegramCommand::getCode).toList();
        bot.sendMessage(chatId, commandAnswer);
    }
}
