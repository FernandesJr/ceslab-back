package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.Registration;
import br.com.ceslab.ceslab.entities.Student;
import br.com.ceslab.ceslab.entities.Team;
import br.com.ceslab.ceslab.projections.ProfitRegistrationYearProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Registration findByStudentAndTeam(Student student, Team team);

    @Query(
            nativeQuery = true,
            value = "SELECT due_date AS duedate, received FROM tb_registration " +
                    "WHERE due_date " +
                    "BETWEEN DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-01') - interval 1 YEAR AND LAST_DAY(CURDATE()) " +
                    "AND received != 0;"
    )
    List<ProfitRegistrationYearProjection> findByOneYearAgo();
}
