package kosyaninchuyko.tgscalping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import ru.tinkoff.piapi.contract.v1.Candle;
import ru.tinkoff.piapi.contract.v1.MarketDataResponse;
import ru.tinkoff.piapi.contract.v1.PortfolioResponse;
import ru.tinkoff.piapi.core.InvestApi;
import ru.tinkoff.piapi.core.SandboxService;

public class ScalpingProcessor {
    private static final String tinkoffAccountToken = "54f4d99e-3502-420d-8ad4-7fa567ac6923";

    private static final Logger log = LoggerFactory.getLogger(CandleProcessor.class);

    @Bean
    public static void createAcc(MarketDataResponse response) {
        SandboxService sandBox = InvestApi.createSandbox(System.getenv("tinkoffToken")).getSandboxService();
        String acc = sandBox.openAccountSync();
        PortfolioResponse myShares = sandBox.getPortfolioSync(acc);
        System.out.println(myShares);
        Candle candle = response.getCandle();
        String low = "\nLOW = " + candle.getLow().getUnits() +"." + candle.getLow().getNano();
        log.info(low);
    }

    @Bean
    public static void trade() {

    }
}
