package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.registration.RegistrationUpdate;
import br.com.ceslab.ceslab.entities.Registration;
import br.com.ceslab.ceslab.repositories.RegistrationRepository;
import br.com.ceslab.ceslab.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository repository;

    @Transactional
    public RegistrationUpdate update(RegistrationUpdate dto, Long id) {
        try {
            Registration entity = repository.getReferenceById(id);
            entity.setReceived(dto.getReceived());
            entity.setDiscount(dto.getDiscount());
            entity.setPaid(calculateIfPaid(dto));
            entity.setPayday(dto.getPayday());
            return new RegistrationUpdate(entity);
        } catch (Exception e) {
            throw new ResourceNotFound("Registration not found with id: " + id);
        }
    }

    private boolean calculateIfPaid(RegistrationUpdate registration) {
        Double PaidMoreDiscount = registration.getReceived() + registration.getDiscount();
        return registration.getPrice() - PaidMoreDiscount <= 0;
    }
}
