package dev.maicol.graphqlCRUD.repository;

import dev.maicol.graphqlCRUD.entity.OneTimePassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OneTimePasswordRepository extends JpaRepository<OneTimePassword, Long> {
   OneTimePassword findByCode(Integer code);
}
