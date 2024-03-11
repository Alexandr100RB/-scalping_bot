package kosyaninchuyko.tgscalping.trade.candle;

import kosyaninchuyko.tgscalping.ShareService;
import kosyaninchuyko.tgscalping.order.Order;
import kosyaninchuyko.tgscalping.order.OrderService;
import ru.tinkoff.piapi.contract.v1.Account;

import java.util.Comparator;
import java.util.List;

public class CandleHandler {
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
        candles.sort(Comparator.comparing(Candle::getSeconds));

        return CandleAnalyticStatus.SUCCESS;
    }
}
