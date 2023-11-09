package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.PaymentVoucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentVoucherRepository extends JpaRepository<PaymentVoucher, Long> {
}
