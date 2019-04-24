package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.model.UserAuthority;

public interface UserAuthorityRepository extends CrudRepository<UserAuthority, Long> {
    public UserAuthority findByAuthority(String role_user);
}
