package kosyaninchuyko.tgscalping.trade.candle;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

/**
 * Класс с информацией о торговой свече
 *
 * @since 10.04.2024
 */

public class Candle {
    @Nonnull
    private final BigDecimal openPrice;
    @Nonnull
    private final BigDecimal closePrice;
    @Nonnull
    private final BigDecimal highPrice;
    @Nonnull
    private final BigDecimal lowPrice;
    @Nonnull
    private final Long seconds;

    private Candle(@Nonnull BigDecimal openPrice,
                   @Nonnull BigDecimal closePrice,
                   @Nonnull BigDecimal highPrice,
                   @Nonnull BigDecimal lowPrice,
                   @Nonnull Long seconds) {
        this.openPrice = requireNonNull(openPrice, "openPrice");
        this.closePrice = requireNonNull(closePrice, "closePrice");
        this.highPrice = requireNonNull(highPrice, "highPrice");
        this.lowPrice = requireNonNull(lowPrice, "lowPrice");
        this.seconds = requireNonNull(seconds, "seconds");
    }

    /**
     * Создает новый объект билдера для {@link Candle}
     */
    @Nonnull
    public static Builder builder() {
        return new Builder();
    }

    @Nonnull
    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    @Nonnull
    public BigDecimal getClosePrice() {
        return closePrice;
    }

    @Nonnull
    public BigDecimal getHighPrice() {
        return highPrice;
    }

    @Nonnull
    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    @Nonnull
    public Long getSeconds() {
        return seconds;
    }

    /**
     * Билдер для {@link Candle}
     */
    public static final class Builder {
        private BigDecimal openPrice;
        private BigDecimal closePrice;
        private BigDecimal highPrice;
        private BigDecimal lowPrice;
        private Long seconds;

        private Builder() {
        }

        public Builder withOpenPrice(@Nonnull BigDecimal openPrice) {
            this.openPrice = openPrice;
            return this;
        }

        public Builder withClosePrice(@Nonnull BigDecimal closePrice) {
            this.closePrice = closePrice;
            return this;
        }

        public Builder withHighPrice(@Nonnull BigDecimal highPrice) {
            this.highPrice = highPrice;
            return this;
        }

        public Builder withLowPrice(@Nonnull BigDecimal lowPrice) {
            this.lowPrice = lowPrice;
            return this;
        }

        public Builder withSeconds(@Nonnull Long seconds) {
            this.seconds = seconds;
            return this;
        }

        /**
         * Собрать объект
         */
        @Nonnull
        public Candle build() {
            return new Candle(openPrice, closePrice, highPrice, lowPrice, seconds);
        }
    }
}
