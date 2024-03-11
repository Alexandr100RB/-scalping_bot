package kosyaninchuyko.tgscalping.trade;

import kosyaninchuyko.tgscalping.order.OrderService;
import kosyaninchuyko.tgscalping.trade.candle.HistoricCandleHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tinkoff.piapi.contract.v1.MarketDataResponse;
import ru.tinkoff.piapi.core.stream.StreamProcessor;

import java.util.Collections;


public class TradeProcessor implements StreamProcessor<MarketDataResponse> {
    private static final Logger log = LoggerFactory.getLogger(TradeProcessor.class);

    private static final double MINIMAL_PERCENT = 1.025;
    private final HistoricCandleHandler historicCandleHandler;
    private final OrderService orderService;

    public TradeProcessor(HistoricCandleHandler historicCandleHandler,
                          OrderService orderService) {
        this.historicCandleHandler = historicCandleHandler;
        this.orderService = orderService;
    }

    @Override
    public void process(MarketDataResponse response) {
        log.info("response = {}", response);
        //Делим текущую стоимость на цену открытия > 1.05
        var realTimeCandleStatus = analyticRealTimeCandle();
        if (realTimeCandleStatus == AnalyticStatus.FAIL) {
            return;
        }

        //Получаем исторические свечки и ебашим алгоритм -> статус
        var historicCandleStatus = historicCandleHandler.handle();

        //Смотрим, чтобы оба вернули саксекс -> заявка
        if (historicCandleStatus == AnalyticStatus.SUCCESS) {

        }
    }

    private AnalyticStatus analyticRealTimeCandle() {
        return AnalyticStatus.SUCCESS;
    }
}
