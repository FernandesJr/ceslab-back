package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.MonthPayment;
import br.com.ceslab.ceslab.entities.Student;
import br.com.ceslab.ceslab.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonthPaymentRepository extends JpaRepository<MonthPayment, Long> {

    List<MonthPayment> findByStudentAndTeam(Student student, Team team);
}
