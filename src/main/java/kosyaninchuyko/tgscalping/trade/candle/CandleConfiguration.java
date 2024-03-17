package kosyaninchuyko.tgscalping.trade.candle;

import kosyaninchuyko.tgscalping.ShareService;
import kosyaninchuyko.tgscalping.account.AccountService;
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
    HistoricCandleHandler historicCandleHandler(InvestApi investApi,
                                        ShareService shareService,
                                        AccountService accountService) {
        return new HistoricCandleHandler(
                accountService.getAccount(),
                shareService,
                investApi.getMarketDataService());
    }
}
