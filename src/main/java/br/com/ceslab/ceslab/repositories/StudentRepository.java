package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.Student;
import br.com.ceslab.ceslab.entities.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.name LIKE %:name%")
    Page<Student> findByNameContaining(String name, Pageable pageable);

    List<Student> findByTeams(Team team);

    Student findByCpf(String cpf);
}
