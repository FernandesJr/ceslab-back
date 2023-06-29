package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
