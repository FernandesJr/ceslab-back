package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.MonthPayment;
import br.com.ceslab.ceslab.entities.Student;
import br.com.ceslab.ceslab.entities.Team;
import br.com.ceslab.ceslab.projections.ProfitMonthPaymentYeahProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonthPaymentRepository extends JpaRepository<MonthPayment, Long> {

    List<MonthPayment> findByStudentAndTeam(Student student, Team team);

    @Query(
            nativeQuery = true,
            value = "SELECT payday, received FROM tb_month_payment " +
            "WHERE due_date " +
            "BETWEEN DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-01') - interval 1 YEAR AND current_date() " +
            "AND received != 0;"
    )
    List<ProfitMonthPaymentYeahProjection> findByOneYeahAgo();
}
