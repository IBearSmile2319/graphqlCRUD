package dev.maicol.graphqlCRUD.repository;

import dev.maicol.graphqlCRUD.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}
