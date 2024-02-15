package kosyaninchuyko.tgscalping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tinkoff.piapi.contract.v1.MarketDataResponse;
import ru.tinkoff.piapi.core.stream.StreamProcessor;


public class CandleProcessor implements StreamProcessor<MarketDataResponse> {
    private static final Logger log = LoggerFactory.getLogger(CandleProcessor.class);
    @Override
    public void process(MarketDataResponse response) {
        log.info("response = {}", response);
    }
}
