package kosyaninchuyko.tgscalping.property;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы со свойствами приложения
 *
 * @author Alexey Chuyko (aachuyko@yoomoney.ru)
 * @since 24.03.2024
 */
@Repository
public interface PropertyRepository extends CrudRepository<Preferences, Long> {
}
