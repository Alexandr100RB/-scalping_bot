package kosyaninchuyko.tgscalping.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Конфигурация для доступа к ключам
 *
 * @since 24.03.2024
 */
@Configuration
@Data
@PropertySource("classpath:/application.properties")
public class PropertyInfo {
    @Value("${tinkoff.token}")
    String tinkoffToken;
    @Value("${bot.token}")
    String botToken;
    @Value("${bot.name}")
    String botName;
}
