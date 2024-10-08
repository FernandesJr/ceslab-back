package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.MonthPayment;
import br.com.ceslab.ceslab.entities.Student;
import br.com.ceslab.ceslab.entities.Team;
import br.com.ceslab.ceslab.projections.AmountNameAndValue;
import br.com.ceslab.ceslab.projections.ProfitMonthPaymentYeahProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MonthPaymentRepository extends JpaRepository<MonthPayment, Long> {

    List<MonthPayment> findByStudentAndTeam(Student student, Team team);

    @Query(
            nativeQuery = true,
            value = "SELECT due_date AS duedate, received FROM tb_month_payment " +
            "WHERE due_date " +
            "BETWEEN DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-01') - interval 1 YEAR AND LAST_DAY(CURDATE()) " +
            "AND received != 0;"
    )
    List<ProfitMonthPaymentYeahProjection> findByOneYeahAgo();

    @Query(
            nativeQuery = true,
            value = "SELECT CONCAT(LPAD(MONTH(m.due_date), 2, '0'), '/', YEAR(m.due_date)) as name, sum(m.received) as value " +
                    "FROM tb_month_payment AS m " +
                    "WHERE m.due_date BETWEEN :dateStart AND :dateEnd " +
                    "GROUP BY name; "
    )
    List<AmountNameAndValue> findAllByGroup(LocalDate dateStart, LocalDate dateEnd);
}
