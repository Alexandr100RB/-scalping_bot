package kosyaninchuyko.tgscalping.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.core.InvestApi;

/**
 * Конфигурация бинов обработки свечей
 *
 * @author Alexey Chuyko
 * @since 11.03.2024
 */
@Configuration
public class OrderConfiguration {
    @Bean
    OrderService orderService(InvestApi investApi) {
        return new OrderService(investApi.getOrdersService());
    }
}
