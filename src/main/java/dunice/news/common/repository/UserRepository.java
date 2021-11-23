package dunice.news.common.repository;


import dunice.news.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByName(String name);
    Optional<UserEntity> findByEmail(String email);
    UserEntity findById(Integer id);
}