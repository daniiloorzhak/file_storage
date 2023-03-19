package ru.oorzhak.filestorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.oorzhak.filestorage.models.Account;

/**
 * Интерфейс для работы с пользователями на уровне БД
 */
public interface UserRepository extends JpaRepository<Account, Long> {
    /**
     * Поиск пользователя в БД по его логину
     *
     * @param username логин пользователя
     * @return сущность пользователя из БД
     */
    Account findByUsername(String username);

    Account findById(long id);
}
