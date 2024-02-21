package kosyaninchuyko.tgscalping;

import java.math.BigDecimal;

public class Candle {
    private final BigDecimal openPrice;
    private final BigDecimal closePrice;
    private final BigDecimal highPrice;
    private final BigDecimal lowPrice;

    public Candle(BigDecimal openPrice, BigDecimal closePrice, BigDecimal highPrice, BigDecimal lowPrice) {
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public static final class CandleBuilder {
        private BigDecimal openPrice;
        private BigDecimal closePrice;
        private BigDecimal highPrice;
        private BigDecimal lowPrice;

        private CandleBuilder() {
        }

        public static CandleBuilder aCandle() {
            return new CandleBuilder();
        }

        public CandleBuilder withOpenPrice(BigDecimal openPrice) {
            this.openPrice = openPrice;
            return this;
        }

        public CandleBuilder withClosePrice(BigDecimal closePrice) {
            this.closePrice = closePrice;
            return this;
        }

        public CandleBuilder withHighPrice(BigDecimal highPrice) {
            this.highPrice = highPrice;
            return this;
        }

        public CandleBuilder withLowPrice(BigDecimal lowPrice) {
            this.lowPrice = lowPrice;
            return this;
        }

        public Candle build() {
            return new Candle(openPrice, closePrice, highPrice, lowPrice);
        }
    }
}
