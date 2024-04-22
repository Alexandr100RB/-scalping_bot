package kosyaninchuyko.tgscalping.telegram.bot.command.order;

import kosyaninchuyko.tgscalping.account.AccountService;
import kosyaninchuyko.tgscalping.telegram.bot.TelegramBot;
import kosyaninchuyko.tgscalping.telegram.bot.command.TelegramCommand;
import kosyaninchuyko.tgscalping.telegram.bot.command.UserCommand;
import ru.tinkoff.piapi.contract.v1.MoneyValue;
import ru.tinkoff.piapi.contract.v1.OrderState;
import ru.tinkoff.piapi.core.InvestApi;

import javax.annotation.Nonnull;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static kosyaninchuyko.tgscalping.telegram.bot.command.CommandRegistry.TG_COMMANDS;

/**
 * Команда для получения активных заявок
 *
 * @since 24.03.2024
 */
public class GetOrdersCommand implements UserCommand {
    private final InvestApi investApi;
    private final TelegramBot telegramBot;
    private final AccountService accountService;

    public GetOrdersCommand(InvestApi investApi,
                            TelegramBot telegramBot,
                            AccountService accountService) {
        this.investApi = investApi;
        this.telegramBot = telegramBot;
        this.accountService = accountService;
        TG_COMMANDS.put(TelegramCommand.GET_ORDERS, this);
    }

    @Override
    public void execute(long chatId, @Nonnull String message) {
        var sandboxService = investApi.getSandboxService();
        var account = accountService.getAccount();
        var ordersByAccount = sandboxService.getOrdersSync(account.getId());
        if (ordersByAccount.isEmpty()) {
            telegramBot.sendMessage(chatId, "No orders by accounts");
        } else {
            telegramBot.sendMessage(chatId, ordersByAccount.size() + " orders by accounts \uD83D\uDC47");
            for (OrderState order: ordersByAccount) {
                telegramBot.sendMessage(chatId, "acc: " + account.getId() + "\n" +
                        orderToString(order));
            }
        }
    }

    public String orderToString(OrderState order) {
        MoneyValue price = order.getInitialOrderPrice();
        return "order id: " + order.getOrderId() + "\ndirection: " + order.getDirection() +
                "\nfigi: " + order.getFigi() +
                "\nlots requested: " + order.getLotsRequested() + "\nprice: " +
                price.getUnits() + "." + price.getNano()/10000000 + " " + price.getCurrency().toUpperCase();
    }
}
