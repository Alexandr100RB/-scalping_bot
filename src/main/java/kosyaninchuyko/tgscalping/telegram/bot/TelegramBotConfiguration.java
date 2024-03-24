package kosyaninchuyko.tgscalping.telegram.bot;

import kosyaninchuyko.tgscalping.account.AccountService;
import kosyaninchuyko.tgscalping.property.PropertyInfo;
import kosyaninchuyko.tgscalping.telegram.bot.command.CommandRegistry;
import kosyaninchuyko.tgscalping.telegram.bot.command.order.GetOrdersCommand;
import kosyaninchuyko.tgscalping.telegram.bot.command.start.StartCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.core.InvestApi;

/**
 * Конфигурация бинов телеграм бота
 *
 * @since 24.03.2024
 */
@Configuration
public class TelegramBotConfiguration {
    @Bean
    BotInitializer botInitializer(TelegramBot telegramBot) {
        return new BotInitializer(telegramBot);
    }

    @Bean
    StartCommand startCommand(TelegramBot bot) {
        return new StartCommand(bot);
    }

    @Bean
    GetOrdersCommand getOrdersCommand(InvestApi investApi,
                                      TelegramBot telegramBot,
                                      AccountService accountService) {
        return new GetOrdersCommand(investApi, telegramBot, accountService);
    }

    @Bean
    TelegramBot telegramBot(PropertyInfo propertyInfo) {
        return new TelegramBot(propertyInfo);
    }
}
