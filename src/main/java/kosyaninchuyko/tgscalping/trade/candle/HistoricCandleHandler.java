package kosyaninchuyko.tgscalping.trade.candle;

import kosyaninchuyko.tgscalping.ShareService;
import kosyaninchuyko.tgscalping.trade.AnalyticStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tinkoff.piapi.contract.v1.Account;
import ru.tinkoff.piapi.core.MarketDataService;

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
//        marketDataService.getCandlesSync();
        return AnalyticStatus.SUCCESS;
    }
}
