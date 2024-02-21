package kosyaninchuyko.tgscalping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tinkoff.piapi.contract.v1.Candle;
import ru.tinkoff.piapi.contract.v1.MarketDataResponse;
import ru.tinkoff.piapi.core.stream.StreamProcessor;

import java.util.ArrayList;
import java.util.List;


public class CandleProcessor implements StreamProcessor<MarketDataResponse> {
    private static final Logger log = LoggerFactory.getLogger(CandleProcessor.class);

    private static final List<Candle> candles = new ArrayList<>();
    private final CandleHandler candleHandler;

    public CandleProcessor(CandleHandler candleHandler) {
        this.candleHandler = candleHandler;
    }

    @Override
    public void process(MarketDataResponse response) {
        log.info("response = {}", response);
        if (candles.isEmpty()) {
            candles.add(response.getCandle());
            return;
        }
        if (candles.size() >= 2) {
            for (int i = 0; i < candles.size()-2; i++) {
                candles.remove(i);
            }
        }
        if (candles.size() == 1) {
            Candle candle = candles.get(0);
            if (candle.getTime().getSeconds() != response.getCandle().getTime().getSeconds()) {
                candles.add(response.getCandle());
            }

        }
        if (candles.size() == 2) {
            candleHandler.handle(candles.stream().map(CandleConverter::toCandle).toList());
        }

//        log.info("\n OPEN = {}\n HIGH = {}\n CLOSE = {}\n LOW = {}",
//                response.getCandle().getOpen(), response.getCandle().getHigh(),
//                response.getCandle().getClose(), response.getCandle().getLow());
        //ScalpingProcessor.createAcc(response);
    }
}
