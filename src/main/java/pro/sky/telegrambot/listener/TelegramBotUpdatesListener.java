package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.services.BotService;
//import pro.sky.telegrambot.services.BotService;
//import pro.sky.telegrambot.services.BotServiceImpl;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final  Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    @Autowired
    private final TelegramBot telegramBot;
    @Autowired
    private final BotService botService;
    public TelegramBotUpdatesListener(TelegramBot telegramBot,BotService botService) {
        this.telegramBot = telegramBot;
        this.botService = botService;
    }
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            if (update.message().text().startsWith("/start")) {
                SendMessage message = new SendMessage(update.message().chat().id(), "hellllllo");
                var result = telegramBot.execute(message);//если убрать резалт - что нибудь изменится?
                if (result.isOk()) {
                    logger.debug("SENT!!");
                } else {
                    logger.warn("NOT SENT!!");
                }
            } else if (update.message().text().startsWith("/add")) {
                botService.addSmth(update.message());
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
