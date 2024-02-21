package kosyaninchuyko.tgscalping;

import ru.tinkoff.piapi.contract.v1.Quotation;

import java.math.BigDecimal;

public class CandleConverter {
    public static Candle toCandle(ru.tinkoff.piapi.contract.v1.Candle candle) {
        return Candle.CandleBuilder.aCandle()
                .withOpenPrice(convertQuotation(candle.getOpen()))
                .withClosePrice(convertQuotation(candle.getClose()))
                .withHighPrice(convertQuotation(candle.getHigh()))
                .withLowPrice(convertQuotation(candle.getLow()))
                .build();
    }

    public static BigDecimal convertQuotation(Quotation quotation) {
        return BigDecimal.valueOf(quotation.getUnits() + quotation.getNano() * Math.pow(10, -9));
    }
}
