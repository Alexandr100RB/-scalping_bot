package kosyaninchuyko.tgscalping.trade.candle;

import com.google.protobuf.InvalidProtocolBufferException;
import kosyaninchuyko.tgscalping.ShareService;
import kosyaninchuyko.tgscalping.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.contract.v1.Account;
import ru.tinkoff.piapi.contract.v1.MoneyValue;
import ru.tinkoff.piapi.core.InvestApi;
import ru.tinkoff.piapi.core.SandboxService;

import java.nio.charset.StandardCharsets;

/**
 * Конфигурация бинов обработки свечей
 *
 * @author Alexey Chuyko
 * @since 11.03.2024
 */
@Configuration
public class CandleConfiguration {
    @Bean
    CandleHandler candleHandler(OrderService orderService,
                                InvestApi investApi,
                                ShareService shareService) {
        var accounts = investApi.getSandboxService().getAccountsSync();
        var sandboxService = investApi.getSandboxService();
        if (accounts.isEmpty()) {
            var accountId = sandboxService.openAccountSync();
            sandboxService.payInSync(
                    accountId,
                    MoneyValue.newBuilder()
                            .setUnits(10000)
                            .setCurrency("4217")
                    .build()
            );
        }
        return new CandleHandler(
                orderService,
                sandboxService.getAccountsSync().stream().findAny().orElseThrow(),
                shareService
        );
    }
}
