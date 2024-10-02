package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.Expense;
import br.com.ceslab.ceslab.projections.AmountNameAndValue;
import br.com.ceslab.ceslab.projections.ProfitRegistrationYearProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM tb_expense " +
            "WHERE emission BETWEEN :start AND :end AND " +
            "(:courseId IS NULL OR course_id = :courseId) " +
            "ORDER BY emission DESC, id DESC")
    List<Expense> findBySearch(LocalDate start, LocalDate end, Long courseId);

    @Query(
            nativeQuery = true,
            value = "SELECT SUM(cost) FROM tb_expense " +
                    "WHERE emission " +
                    "BETWEEN DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-01') - interval 1 YEAR AND LAST_DAY(CURDATE()) "
    )
    Double findValueByOneYearAgo();

    @Query(
            nativeQuery = true,
            value = "SELECT CONCAT(LPAD(MONTH(e.emission), 2, '0'), '/', YEAR(e.emission)) as name, sum(e.cost) as value " +
                    "FROM tb_expense AS e " +
                    "GROUP BY name; "
    )
    List<AmountNameAndValue> findAllByGroup();
}
