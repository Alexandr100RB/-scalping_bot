package kosyaninchuyko.tgscalping.trade;

import kosyaninchuyko.tgscalping.trade.candle.CandleConverter;
import kosyaninchuyko.tgscalping.trade.candle.CandleHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tinkoff.piapi.contract.v1.Candle;
import ru.tinkoff.piapi.contract.v1.MarketDataResponse;
import ru.tinkoff.piapi.core.stream.StreamProcessor;

import java.util.ArrayList;
import java.util.List;


public class TradeProcessor implements StreamProcessor<MarketDataResponse> {
    private static final Logger log = LoggerFactory.getLogger(TradeProcessor.class);

    private static final List<Candle> candles = new ArrayList<>();
    private final CandleHandler candleHandler;

    public TradeProcessor(CandleHandler candleHandler) {
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
    }
}
