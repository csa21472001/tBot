package pro.sky.telegrambot.models;

import javax.persistence.*;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;
@Entity
@Table( name = "task_notifications")
public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long chatId;
    @Column(name = "notification_date_time")
    private LocalDateTime dateTime;
    private String message ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return chatId == that.chatId && Objects.equals(dateTime, that.dateTime) && Objects.equals(message, that.message);
    }

    public long getChatId() {
        return chatId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, dateTime, message);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
