package kosyaninchuyko.tgscalping.property;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Свойство
 *
 * @author Alexey Chuyko (aachuyko@yoomoney.ru)
 * @since 24.03.2024
 */
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(schema = "public", name = "preferences")
public class Preferences {
    @Id
    @org.springframework.data.annotation.Id
    @Column(name = "id")
    private Long id;

    @Column(name = "tinkoff_token")
    private String tinkoffToken;

    @Column(name = "telegram_bot_name")
    private String telegramBotName;

    @Column(name = "telegram_bot_token")
    private String telegramBotToken;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Preferences preferences = (Preferences) o;

        if (!Objects.equals(id, preferences.id)) {
            return false;
        }
        if (!Objects.equals(tinkoffToken, preferences.tinkoffToken)) {
            return false;
        }
        if (!Objects.equals(telegramBotName, preferences.telegramBotName)) {
            return false;
        }
        return Objects.equals(telegramBotToken, preferences.telegramBotToken);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tinkoffToken != null ? tinkoffToken.hashCode() : 0);
        result = 31 * result + (telegramBotName != null ? telegramBotName.hashCode() : 0);
        result = 31 * result + (telegramBotToken != null ? telegramBotToken.hashCode() : 0);
        return result;
    }
}
