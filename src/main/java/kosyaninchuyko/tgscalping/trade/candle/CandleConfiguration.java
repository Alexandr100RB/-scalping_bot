package kosyaninchuyko.tgscalping.trade.candle;

import kosyaninchuyko.tgscalping.ShareService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.contract.v1.MoneyValue;
import ru.tinkoff.piapi.core.InvestApi;

/**
 * Конфигурация бинов обработки свечей
 *
 * @author Alexey Chuyko
 * @since 11.03.2024
 */
@Configuration
public class CandleConfiguration {
    @Bean
    HistoricCandleHandler candleHandler(InvestApi investApi,
                                        ShareService shareService) {
        var accounts = investApi.getSandboxService().getAccountsSync();
        var sandboxService = investApi.getSandboxService();
        if (accounts.isEmpty()) {
            var accountId = sandboxService.openAccountSync();
            sandboxService.payInSync(
                    accountId,
                    MoneyValue.newBuilder()
                            .setUnits(10000)
                            .setCurrency("4217")
                    .build()
            );
        }
        return new HistoricCandleHandler(
                sandboxService.getAccountsSync().stream().findAny().orElseThrow(),
                shareService,
                investApi.getMarketDataService());
    }
}
