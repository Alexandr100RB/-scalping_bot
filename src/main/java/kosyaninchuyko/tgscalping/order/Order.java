package kosyaninchuyko.tgscalping.order;


import ru.tinkoff.piapi.contract.v1.OrderDirection;
import ru.tinkoff.piapi.contract.v1.OrderType;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

/**
 * Информация о заявкк
 *
 * @author Alexey Chuyko
 * @since 24.02.2024
 */
public class Order {
    @Nonnull
    private final String intrumentId;
    private final long quantity;
    @Nonnull
    private final BigDecimal price;
    @Nonnull
    private final OrderDirection orderDirection;
    @Nonnull
    private final String accountId;
    @Nonnull
    private final OrderType orderType;

    private Order(@Nonnull String intrumentId,
                  long quantity,
                  @Nonnull BigDecimal price,
                  @Nonnull OrderDirection orderDirection,
                  @Nonnull String accountId,
                  @Nonnull OrderType orderType) {
        this.intrumentId = requireNonNull(intrumentId, "intrumentId");
        this.quantity = quantity;
        this.price = requireNonNull(price, "price");
        this.orderDirection = requireNonNull(orderDirection, "orderDirection");
        this.accountId = requireNonNull(accountId, "accountId");
        this.orderType = requireNonNull(orderType, "orderType");
    }

    /**
     * Создает новый объект билдера для {@link Order}
     */
    @Nonnull
    public static Builder builder() {
        return new Builder();
    }

    @Nonnull
    public String getIntrumentId() {
        return intrumentId;
    }

    public long getQuantity() {
        return quantity;
    }

    @Nonnull
    public BigDecimal getPrice() {
        return price;
    }

    @Nonnull
    public OrderDirection getOrderDirection() {
        return orderDirection;
    }

    @Nonnull
    public String getAccountId() {
        return accountId;
    }

    @Nonnull
    public OrderType getOrderType() {
        return orderType;
    }

    /**
     * Билдер для {@link Order}
     */
    public static final class Builder {
        private String intrumentId;
        private long quantity;
        private BigDecimal price;
        private OrderDirection orderDirection;
        private String accountId;
        private OrderType orderType;

        private Builder() {
        }

        public Builder withIntrumentId(@Nonnull String intrumentId) {
            this.intrumentId = intrumentId;
            return this;
        }

        public Builder withQuantity(long quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withPrice(@Nonnull BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withOrderDirection(@Nonnull OrderDirection orderDirection) {
            this.orderDirection = orderDirection;
            return this;
        }

        public Builder withAccountId(@Nonnull String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder withOrderType(@Nonnull OrderType orderType) {
            this.orderType = orderType;
            return this;
        }

        /**
         * Собрать объект
         */
        @Nonnull
        public Order build() {
            return new Order(intrumentId, quantity, price, orderDirection, accountId, orderType);
        }
    }
}
