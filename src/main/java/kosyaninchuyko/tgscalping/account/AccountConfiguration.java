package kosyaninchuyko.tgscalping.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.core.InvestApi;

/**
 * Конфигурация бинов для работы с торговыми аккаунтами
 *
 * @author Alexey Chuyko
 * @since 13.03.2024
 */
@Configuration
public class AccountConfiguration {
    @Bean
    AccountService accountService(InvestApi investApi) {
        return new AccountService(investApi);
    }
}
