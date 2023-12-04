package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.Degree;
import br.com.ceslab.ceslab.entities.Student;
import br.com.ceslab.ceslab.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DegreeRepository extends JpaRepository<Degree, Long> {

    Degree findByCode(String code);

    Optional<Degree> findByStudentAndTeam(Student student, Team team);
}
