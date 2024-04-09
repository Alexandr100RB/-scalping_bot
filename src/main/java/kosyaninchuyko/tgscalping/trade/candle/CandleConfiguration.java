package kosyaninchuyko.tgscalping.trade.candle;

import kosyaninchuyko.tgscalping.ShareService;
import kosyaninchuyko.tgscalping.account.AccountService;
import kosyaninchuyko.tgscalping.trade.TradeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.contract.v1.MoneyValue;
import ru.tinkoff.piapi.core.InvestApi;
import static kosyaninchuyko.tgscalping.utils.ApiUtils.toBigDecimal;

/**
 * Конфигурация бинов обработки свечей
 *
 * @author Alexey Chuyko
 * @since 11.03.2024
 */
@Configuration
public class CandleConfiguration {
    private static final Logger log = LoggerFactory.getLogger(TradeProcessor.class);
    @Bean
    HistoricCandleHandler historicCandleHandler(InvestApi investApi,
                                        ShareService shareService,
                                        AccountService accountService) {
        var sandboxService = investApi.getSandboxService();
        var portfolio = sandboxService.getPortfolioSync(accountService.getAccount().getId());
        log.info(portfolio.toString());
        log.info("\n    Account ID = " + portfolio.getAccountId() + "\n    Total Amount Portfolio = "
                +portfolio.getTotalAmountPortfolio().getUnits() + "." +
                portfolio.getTotalAmountPortfolio().getNano()/10000000 + " " +
                portfolio.getTotalAmountPortfolio().getCurrency());
        return new HistoricCandleHandler(
                accountService.getAccount(),
                shareService,
                investApi.getMarketDataService());
    }
}
