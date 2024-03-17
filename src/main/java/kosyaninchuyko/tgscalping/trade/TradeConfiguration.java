package kosyaninchuyko.tgscalping.trade;

import kosyaninchuyko.tgscalping.ShareService;
import kosyaninchuyko.tgscalping.account.AccountService;
import kosyaninchuyko.tgscalping.order.OrderService;
import kosyaninchuyko.tgscalping.trade.candle.HistoricCandleHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.contract.v1.Share;
import ru.tinkoff.piapi.core.InvestApi;
import ru.tinkoff.piapi.core.stream.MarketDataStreamService;
import ru.tinkoff.piapi.core.stream.MarketDataSubscriptionService;

import java.util.List;
import java.util.Optional;

@Configuration
public class TradeConfiguration {
    private static final String STREAM_ID = "tradeStream";
    private static final Logger log = LoggerFactory.getLogger(TradeConfiguration.class);


    @Bean
    public InvestApi investApi() {
        return  InvestApi.createSandbox(
                System.getenv("tinkoffToken")
        );
    }
    @Bean
    public MarketDataStreamService marketDataStreamService(InvestApi investApi) {
        return investApi.getMarketDataStreamService();
    }

    @Bean
    public TradeProcessor tradeProcessor(HistoricCandleHandler historicCandleHandler,
                                         OrderService orderService,
                                         AccountService accountService,
                                         ShareService shareService) {
        return new TradeProcessor(
                historicCandleHandler,
                orderService,
                accountService,
                shareService
        );
    }

    @Bean
    public ShareService shareService(InvestApi investApi) {
        return new ShareService(investApi.getInstrumentsService().getTradableSharesSync());
    }

    @Bean
    public MarketDataSubscriptionService marketDataSubscriptionService(
            MarketDataStreamService marketDataStreamService,
            TradeProcessor tradeProcessor,
            ShareService shareService) {
        MarketDataSubscriptionService service = marketDataStreamService.newStream(STREAM_ID, tradeProcessor,
                error -> log.error("Error happened: error={}", error.getMessage()));
        Optional<Share> share = shareService.getShareByTicker("YNDX");
        if (share.isEmpty()) {
            throw new RuntimeException("Share was not found");
        }
        service.subscribeCandles(List.of(share.get().getFigi()));
        return service;
    }

}