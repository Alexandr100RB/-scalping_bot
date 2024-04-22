package kosyaninchuyko.tgscalping.telegram.bot.command.portfolio;

import kosyaninchuyko.tgscalping.account.AccountService;
import kosyaninchuyko.tgscalping.telegram.bot.TelegramBot;
import kosyaninchuyko.tgscalping.telegram.bot.command.TelegramCommand;
import kosyaninchuyko.tgscalping.telegram.bot.command.UserCommand;
import ru.tinkoff.piapi.core.InvestApi;

import javax.annotation.Nonnull;

import static kosyaninchuyko.tgscalping.telegram.bot.command.CommandRegistry.TG_COMMANDS;
import static kosyaninchuyko.tgscalping.utils.ApiUtils.toBigDecimal;

/**
 * Команда для получения информации о балансе
 *
 * @since 24.03.2024
 */
public class GetPortfolioCommand implements UserCommand {
    private final InvestApi investApi;
    private final TelegramBot telegramBot;
    private final AccountService accountService;

    public GetPortfolioCommand(InvestApi investApi,
                            TelegramBot telegramBot,
                            AccountService accountService) {
        this.investApi = investApi;
        this.telegramBot = telegramBot;
        this.accountService = accountService;
        TG_COMMANDS.put(TelegramCommand.GET_PORTFOLIO, this);
    }

    @Override
    public void execute(long chatId, @Nonnull String message) {
        var sandboxService = investApi.getSandboxService();
        var accountId = accountService.getAccount().getId();
        var portfolio = sandboxService.getPortfolioSync(accountId);
        telegramBot.sendMessage(chatId, "Account ID: \n" + accountId);
        telegramBot.sendMessage(chatId, "Total amount portfolio: " +
                portfolio.getTotalAmountPortfolio().getUnits() + "." +
                portfolio.getTotalAmountPortfolio().getNano()/10000000 +
                portfolio.getTotalAmountPortfolio().getCurrency().toUpperCase());
    }

}
