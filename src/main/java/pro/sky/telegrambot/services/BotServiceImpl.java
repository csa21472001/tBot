package pro.sky.telegrambot.services;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.helpers.Parser;
import pro.sky.telegrambot.models.NotificationTask;
import pro.sky.telegrambot.repositories.TaskNotificationRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class BotServiceImpl implements BotService {
    private final Logger logger = LoggerFactory.getLogger(BotServiceImpl.class);
    private final TelegramBot telegramBot;
    private final TaskNotificationRepository repository;

    public BotServiceImpl(TelegramBot telegramBot, TaskNotificationRepository repository) {
        this.telegramBot = telegramBot;
        this.repository = repository;
    }

    @Override
    public void addSmth(Message message) {
        NotificationTask task;
        long chatId = message.chat().id();
        SendMessage result;
        //parser
        try {
            task = Parser.tryParseNotificationTask(message.text());
            task.setChatId(chatId);
        } catch (Exception e) {
            result = new SendMessage(chatId, "wrong format date or time!!!");
            telegramBot.execute(result);
            return;
        }
        // send to db
        repository.save(task);
        //send response
        result = new SendMessage(chatId, String.format("okkkkkk %s, %s", task.getDateTime(), task.getMessage()) );
        telegramBot.execute(result);
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void NotificationsNow() {
        var time = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        var result = repository.findByDateTime(time);
        for (var t : result) {
            var response = new SendMessage(t.getChatId(), t.getMessage());
            telegramBot.execute(response);
        }
    }

}
