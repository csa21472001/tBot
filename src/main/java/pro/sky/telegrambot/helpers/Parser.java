package pro.sky.telegrambot.helpers;

import pro.sky.telegrambot.models.NotificationTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static NotificationTask tryParseNotificationTask (String dateAndTime) {
        Pattern pattern = Pattern.compile("([0-9.:\\s]{16})(\\s)(.+)");
        Matcher matcher = pattern.matcher(dateAndTime);
        NotificationTask notificationTask = new NotificationTask();
        if (matcher.matches()) {
            LocalDateTime dateTime = LocalDateTime.parse(matcher.group(2), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            notificationTask.setDateTime(dateTime);
            notificationTask.setMessage(matcher.group(4));//
        } else {
            throw new IllegalArgumentException("bad date time");
        }
        return notificationTask;
    }

}
