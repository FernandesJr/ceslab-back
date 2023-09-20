package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.monthPayment.MonthPaymentUpdateDto;
import br.com.ceslab.ceslab.entities.MonthPayment;
import br.com.ceslab.ceslab.repositories.MonthPaymentRepository;
import br.com.ceslab.ceslab.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MonthPaymentService {

    @Autowired
    private MonthPaymentRepository repository;

    @Transactional
    public MonthPaymentUpdateDto update(MonthPaymentUpdateDto dto, Long id) {
        try {
            MonthPayment entity = this.repository.getReferenceById(id);
            entity.setPrice(dto.getPrice());
            entity.setDiscount(dto.getDiscount());
            entity.setReceived(dto.getReceived());
            entity.setPaid(calculationIfPaid(dto));
            entity.setPayday(dto.getPayday());

            return new MonthPaymentUpdateDto(entity);
        } catch (Exception e ) {
            throw new ResourceNotFound("MonthPayment not found with id " + id);
        }
    }

    private boolean calculationIfPaid(MonthPaymentUpdateDto dto) {
        return dto.getPrice() - dto.getDiscount() <= dto.getReceived();
    }

}
