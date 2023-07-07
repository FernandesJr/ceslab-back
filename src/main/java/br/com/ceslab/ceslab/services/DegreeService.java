package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.DegreeDTO;
import br.com.ceslab.ceslab.entities.Degree;
import br.com.ceslab.ceslab.repositories.DegreeRepository;
import br.com.ceslab.ceslab.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DegreeService {

    @Autowired
    private DegreeRepository repository;

    @Transactional(readOnly = true)
    public DegreeDTO findByCode(String code) {
        Degree entity = repository.findByCode(code);
        if (entity == null) throw new ResourceNotFound("Code invalid");
        return new DegreeDTO(entity);
    }
}
