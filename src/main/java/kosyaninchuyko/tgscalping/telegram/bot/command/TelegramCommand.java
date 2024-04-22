package kosyaninchuyko.tgscalping.telegram.bot.command;

import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * Команды для бота телеграмма
 *
 * @since 24.03.2024
 */
public enum TelegramCommand {
    START("/start"),
    GET_PORTFOLIO("/portfolio"),
    GET_ORDERS("/orders");

    private final String code;

    TelegramCommand(String code) {
        this.code = code;
    }

    public static TelegramCommand byCode(@Nonnull String code) {
        return Arrays.stream(TelegramCommand.values()).filter(command -> command.code.equals(code)).findFirst().orElseThrow();
    }

    public String getCode() {
        return code;
    }
}
