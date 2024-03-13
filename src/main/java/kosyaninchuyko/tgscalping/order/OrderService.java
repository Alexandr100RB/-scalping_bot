package kosyaninchuyko.tgscalping.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tinkoff.piapi.core.OrdersService;

import javax.annotation.Nonnull;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static kosyaninchuyko.tgscalping.utils.ApiUtils.toQuotation;

/**
 * Сервис для работы с заявками
 *
 * @author Alexey Chuyko
 * @since 24.02.2024
 */
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrdersService ordersService;

    public OrderService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /**
     * Создание торговой заявки
     *
     * @param order торговая заявка
     */
    public void createOrder(@Nonnull Order order) {
        requireNonNull(order, "order");
        log.info("Create order: order={}", order);
        ordersService.postOrder(
                order.getIntrumentId(),
                order.getQuantity(),
                toQuotation(order.getPrice()),
                order.getOrderDirection(),
                order.getAccountId(),
                order.getOrderType(),
                UUID.randomUUID().toString()
        );
    }
}
