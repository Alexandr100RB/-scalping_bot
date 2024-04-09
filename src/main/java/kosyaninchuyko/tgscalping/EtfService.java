package kosyaninchuyko.tgscalping;

import ru.tinkoff.piapi.contract.v1.Etf;

import java.util.List;
import java.util.Optional;

public class EtfService {
    private final List<Etf> etfList;

    public EtfService(List<Etf> etfList) {
        this.etfList = etfList;
    }

    public List<Etf> getEtfList() {
        return etfList;
    }

    public Optional<Etf> getEtfByTicker(String ticker) {
        return etfList.stream()
                .filter(etf -> ticker.equals(etf.getTicker()))
                .findFirst();
    }
}
