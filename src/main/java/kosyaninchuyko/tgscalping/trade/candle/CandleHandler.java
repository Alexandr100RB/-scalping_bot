package kosyaninchuyko.tgscalping.trade.candle;

import kosyaninchuyko.tgscalping.ShareService;
import kosyaninchuyko.tgscalping.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tinkoff.piapi.contract.v1.Account;

import java.util.List;

public class CandleHandler {
    private static final Logger log = LoggerFactory.getLogger(CandleHandler.class);
    private final OrderService orderService;
    private final Account account;
    private final ShareService shareService;

    public CandleHandler(OrderService orderService,
                         Account account,
                         ShareService shareService) {
        this.orderService = orderService;
        this.account = account;
        this.shareService = shareService;
    }

    public CandleAnalyticStatus handle(List<Candle> candles) {
        return CandleAnalyticStatus.SUCCESS;
    }
}
