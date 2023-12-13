package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.entities.Expense;
import br.com.ceslab.ceslab.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    @Transactional(readOnly = true)
    public List<Expense> findSearch(LocalDate start, LocalDate end, Long courseId) {
        if (start == null) start = LocalDate.now().minusMonths(1);
        if (end == null) end = LocalDate.now();
        return repository.findBySearch(start, end, courseId);
    }

    @Transactional
    public Expense create(Expense dto) {
        if (dto.getEmission() == null) dto.setEmission(LocalDate.now());
        return repository.save(dto);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Expense update(Expense dto, Long id) {
        dto.setId(id);
        return repository.save(dto);
    }
}
