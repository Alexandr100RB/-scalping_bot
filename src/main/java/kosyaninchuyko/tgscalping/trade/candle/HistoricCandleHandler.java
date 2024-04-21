package kosyaninchuyko.tgscalping.trade.candle;

import kosyaninchuyko.tgscalping.trade.AnalyticStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tinkoff.piapi.contract.v1.CandleInterval;
import ru.tinkoff.piapi.contract.v1.HistoricCandle;
import ru.tinkoff.piapi.core.MarketDataService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;

import static kosyaninchuyko.tgscalping.utils.ApiUtils.toBigDecimal;

/**
 * Класс для работы с историческими свечами
 *
 * @since 10.04.2024
 */

public class HistoricCandleHandler {
    private static final Logger log = LoggerFactory.getLogger(HistoricCandleHandler.class);
    private final MarketDataService marketDataService;

    public HistoricCandleHandler(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    public AnalyticStatus handle() {
        Instant currentTime = Instant.now();
        List<HistoricCandle> historicCandles = marketDataService.getCandlesSync("BBG006L8G4H1",
                currentTime.minusSeconds(300),
                currentTime,
                CandleInterval.CANDLE_INTERVAL_1_MIN);
        int size = historicCandles.size();
        if (size < 2) {
            return AnalyticStatus.UNDEFINED;
        }
        historicCandles.sort(Comparator.comparingInt(candle -> candle.getTime().getNanos()));
        HistoricCandle previousCandle = historicCandles.get(size-2);
        HistoricCandle currentCandle = historicCandles.get(size-1);
        BigDecimal previousCandleRate = toBigDecimal(previousCandle.getOpen())
                .divide(toBigDecimal(previousCandle.getClose()), 3, RoundingMode.FLOOR);
        BigDecimal currentCandleRate = toBigDecimal(currentCandle.getOpen())
                .divide(toBigDecimal(currentCandle.getClose()), 3, RoundingMode.FLOOR);
        log.info("""
                        Previous candle open={}\s
                        Previous candle close={}\s
                        Rate={}
                        Current candle open={}
                        Current candle close={}
                        Rate={}""",
                toBigDecimal(previousCandle.getOpen()),
                toBigDecimal(previousCandle.getClose()),
                previousCandleRate,
                toBigDecimal(currentCandle.getOpen()),
                toBigDecimal(currentCandle.getClose()),
                currentCandleRate);
        if (previousCandleRate.compareTo(currentCandleRate) < 0) {
            log.info("Успех по историческим свечкам");
            return AnalyticStatus.SUCCESS;
        } else {
            log.info("Неуспех по историческим свечкам");
            return AnalyticStatus.FAIL;
        }
    }
}
