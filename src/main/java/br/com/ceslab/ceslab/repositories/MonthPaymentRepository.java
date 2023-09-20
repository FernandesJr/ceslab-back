package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.MonthPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthPaymentRepository extends JpaRepository<MonthPayment, Long> {
}
