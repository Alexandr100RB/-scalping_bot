package kosyaninchuyko.tgscalping.trade.candle;

import kosyaninchuyko.tgscalping.ShareService;
import kosyaninchuyko.tgscalping.trade.AnalyticStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tinkoff.piapi.contract.v1.Account;
import ru.tinkoff.piapi.contract.v1.CandleInterval;
import ru.tinkoff.piapi.contract.v1.HistoricCandle;
import ru.tinkoff.piapi.core.MarketDataService;
import static kosyaninchuyko.tgscalping.utils.ApiUtils.toBigDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class HistoricCandleHandler {
    private static final Logger log = LoggerFactory.getLogger(HistoricCandleHandler.class);
    private final Account account;
    private final ShareService shareService;
    private final MarketDataService marketDataService;

    public HistoricCandleHandler(Account account,
                                 ShareService shareService,
                                 MarketDataService marketDataService) {
        this.account = account;
        this.shareService = shareService;
        this.marketDataService = marketDataService;
    }

    public AnalyticStatus handle() {
        List<HistoricCandle> historicCandles = marketDataService.getCandlesSync("BBG006L8G4H1",
                Instant.now().minus(10, ChronoUnit.MINUTES),
                Instant.now(),
                CandleInterval.CANDLE_INTERVAL_1_MIN);
        int size = historicCandles.size();
        HistoricCandle previousCandle = historicCandles.get(size-2);
        HistoricCandle currentCandle = historicCandles.get(size-1);
        BigDecimal previousCandleRate = toBigDecimal(previousCandle.getOpen())
                .divide(toBigDecimal(previousCandle.getClose()), 3, RoundingMode.FLOOR);
        BigDecimal currentCandleRate = toBigDecimal(currentCandle.getOpen())
                .divide(toBigDecimal(currentCandle.getClose()), 3, RoundingMode.FLOOR);
        System.out.println("Previous candle open = " + toBigDecimal(previousCandle.getOpen())
                        + ", Previous candle close = " + toBigDecimal(previousCandle.getClose())
                        + ", Rate = " + previousCandleRate
                        + "\nCurrent candle open = " + toBigDecimal(currentCandle.getOpen())
                        + ", Current candle close " + toBigDecimal(currentCandle.getClose())
                        + ", Rate = " + currentCandleRate);
        if (previousCandleRate.compareTo(currentCandleRate) > 0) {
            System.out.println("Успех по историческим свечкам");
            return AnalyticStatus.SUCCESS;
        } else {
            System.out.println("Неуспех по историческим свечкам");
            return AnalyticStatus.FAIL;
        }
    }
}
