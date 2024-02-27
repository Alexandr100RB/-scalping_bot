package kosyaninchuyko.tgscalping;

import ru.tinkoff.piapi.contract.v1.Share;

import java.util.List;
import java.util.Optional;

public class ShareService {
    private final List<Share> shareList;

    public ShareService(List<Share> shareList) {
        this.shareList = shareList;
    }

    public List<Share> getShareList() {
        return shareList;
    }

    public Optional<Share> getShareByTicker(String ticker) {
        return shareList.stream()
                .filter(share -> ticker.equals(share.getTicker()))
                .findFirst();
    }
}
