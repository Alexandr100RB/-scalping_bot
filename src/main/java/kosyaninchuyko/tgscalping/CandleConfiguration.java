package kosyaninchuyko.tgscalping;

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
public class CandleConfiguration {
    private static final String STREAM_ID = "candleStream";
    private static final Logger log = LoggerFactory.getLogger(CandleConfiguration.class);


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
    public CandleProcessor candleProcessor() {
        return new CandleProcessor(candleHandler);
    }

    @Bean
    public TinkoffService tinkoffService(InvestApi investApi) {
        return new TinkoffService(investApi.getInstrumentsService().getTradableSharesSync());
    }

    @Bean
    public MarketDataSubscriptionService marketDataSubscriptionService(
            MarketDataStreamService marketDataStreamService,
            CandleProcessor candleProcessor,
            TinkoffService tinkoffService) {
        MarketDataSubscriptionService service = marketDataStreamService.newStream(STREAM_ID, candleProcessor,
                error -> log.error("Error happened: error={}", error.getMessage()));
        Optional<Share> share = tinkoffService.getShareByTicker("YNDX");
        if (share.isEmpty()) {
            throw new RuntimeException("We didn't find it");
        }
        service.subscribeCandles(List.of(share.get().getFigi()));
        return service;
    }

}