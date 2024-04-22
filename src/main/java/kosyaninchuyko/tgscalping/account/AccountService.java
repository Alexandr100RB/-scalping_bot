package kosyaninchuyko.tgscalping.account;

import ru.tinkoff.piapi.contract.v1.Account;
import ru.tinkoff.piapi.contract.v1.MoneyValue;
import ru.tinkoff.piapi.core.InvestApi;

/**
 * Сервис для работы с аккаунтами
 *
 * @author Alexey Chuyko
 * @since 13.03.2024
 */
public class AccountService {
    private final InvestApi investApi;

    public AccountService(InvestApi investApi) {
        this.investApi = investApi;
    }

    /**
     * Получение аккаунта в песочнице, готового для торгов
     * <p>Если к песочному счету не привязан ни один аккаунт, то будет создан новый аккаунт с пополненным балансов</p>
     *
     * @return Торговый аккаунт
     */
    public Account getAccount() {
        var accounts = investApi.getSandboxService().getAccountsSync();
        var sandboxService = investApi.getSandboxService();
        if (accounts.isEmpty()) {
            var accountId = sandboxService.openAccountSync();
            sandboxService.payInSync(
                    accountId,
                    MoneyValue.newBuilder()
                            .setUnits(50000)
                            .setCurrency("4217")
                            .build()
            );
        }
        return sandboxService.getAccountsSync().stream().findAny().orElseThrow();
    }
}
