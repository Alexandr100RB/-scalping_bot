package kosyaninchuyko.tgscalping.telegram.bot;

import kosyaninchuyko.tgscalping.order.Order;

import javax.annotation.Nonnull;

/**
 * Класс для отправки уведомлений по событиям
 *
 * @since 24.03.2024
 */
public class TgEventNotifier {
    private final TelegramBot telegramBot;

    public TgEventNotifier(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void notifyOrderCreated(@Nonnull Order order) {

    }
}
