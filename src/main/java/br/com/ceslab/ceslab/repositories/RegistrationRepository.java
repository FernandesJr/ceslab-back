package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.Registration;
import br.com.ceslab.ceslab.entities.Student;
import br.com.ceslab.ceslab.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Registration findByStudentAndTeam(Student student, Team team);
}
