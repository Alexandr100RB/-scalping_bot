package kosyaninchuyko.tgscalping.utils;

import ru.tinkoff.piapi.contract.v1.Quotation;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Утилитарный класс для работы с данными tinkoff api
 *
 * @author Alexey Chuyko
 * @since 24.02.2024
 */
public class ApiUtils {
    public static BigDecimal toBigDecimal(Quotation quotation) {
        return BigDecimal.valueOf(quotation.getUnits() + quotation.getNano() * Math.pow(10, -9));
    }

    public static Quotation toQuotation(BigDecimal num) {
        return Quotation.newBuilder()
                .setUnits(num.setScale(0, RoundingMode.DOWN).longValue())
                .setNano(
                        num.remainder(BigDecimal.ONE).multiply(
                                BigDecimal.valueOf(Math.pow(10, 9))
                        ).intValue()
                )
                .build();
    }
}
