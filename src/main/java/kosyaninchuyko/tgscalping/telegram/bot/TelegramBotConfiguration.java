package kosyaninchuyko.tgscalping.telegram.bot;

import kosyaninchuyko.tgscalping.account.AccountService;
import kosyaninchuyko.tgscalping.property.PropertyRepository;
import kosyaninchuyko.tgscalping.telegram.bot.command.order.GetOrdersCommand;
import kosyaninchuyko.tgscalping.telegram.bot.command.start.StartCommand;
import kosyaninchuyko.tgscalping.telegram.bot.command.portfolio.GetPortfolioCommand;

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
    GetPortfolioCommand getPortfolioCommand(InvestApi investApi,
                                      TelegramBot telegramBot,
                                      AccountService accountService) {
        return new GetPortfolioCommand(investApi, telegramBot, accountService);
    }

    @Bean
    TelegramBot telegramBot(PropertyRepository propertyRepository) {
        return new TelegramBot(propertyRepository);
    }
}
