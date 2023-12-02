package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.Expense;
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
    public List<Expense> findBySearch(LocalDate start, LocalDate end, Long courseId);
}