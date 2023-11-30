package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.entities.Expense;
import br.com.ceslab.ceslab.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseResource {

    @Autowired
    private ExpenseService service;

    @GetMapping("/search")
    public ResponseEntity<List<Expense>> findSearch(
            @RequestParam(name = "course", required = false) Long courseId,
            @RequestParam(name = "start", required = false) LocalDate start,
            @RequestParam(name = "end", required = false) LocalDate end
            ) {
        List<Expense> dto = service.findSearch(start, end, courseId);
        return ResponseEntity.ok(dto);
    }
}
