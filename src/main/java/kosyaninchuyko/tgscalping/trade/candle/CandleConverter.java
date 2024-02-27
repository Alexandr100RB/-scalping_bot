package kosyaninchuyko.tgscalping.trade.candle;

import kosyaninchuyko.tgscalping.utils.ApiUtils;

public final class CandleConverter {
    private CandleConverter() {
    }

    public static Candle toCandle(ru.tinkoff.piapi.contract.v1.Candle candle) {
        return Candle.builder()
                .withOpenPrice(ApiUtils.toBigDecimal(candle.getOpen()))
                .withClosePrice(ApiUtils.toBigDecimal(candle.getClose()))
                .withHighPrice(ApiUtils.toBigDecimal(candle.getHigh()))
                .withLowPrice(ApiUtils.toBigDecimal(candle.getLow()))
                .withSeconds(candle.getTime().getSeconds())
                .build();
    }
}
