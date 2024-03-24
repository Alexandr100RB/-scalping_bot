package kosyaninchuyko.tgscalping.telegram.bot;

import kosyaninchuyko.tgscalping.property.PropertyRepository;
import kosyaninchuyko.tgscalping.telegram.bot.command.TelegramCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static kosyaninchuyko.tgscalping.telegram.bot.command.CommandRegistry.TG_COMMANDS;

/**
 * Главный класс для доступа к апи телеграма
 *
 * @since 24.03.2024
 */
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(TelegramBot.class);

    private final PropertyRepository propertyRepository;
    public TelegramBot(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public String getBotUsername() {
        return propertyRepository.findById(1L).orElseThrow().getTelegramBotName();
    }

    @Override
    public String getBotToken() {
        return propertyRepository.findById(1L).orElseThrow().getTelegramBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Getting update command: update={}", update);
        if (!update.hasMessage()) {
            return;
        }
        if (!update.getMessage().hasText()) {
            return;
        }
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        var command = TelegramCommand.byCode(messageText);
        TG_COMMANDS.get(command).execute(chatId, messageText);
    }

    public void sendMessage(Long chatId, String textToSend){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException ignored) {

        }
    }
}
