package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.models.NotificationTask;

import java.time.LocalDateTime;
import java.util.Collection;

public interface TaskNotificationRepository extends JpaRepository<NotificationTask, Long>
{
    Collection<NotificationTask> findByDateTime(LocalDateTime dateTime);
}
