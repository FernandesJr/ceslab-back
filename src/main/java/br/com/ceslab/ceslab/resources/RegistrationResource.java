package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.registration.RegistrationUpdate;
import br.com.ceslab.ceslab.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationResource {

    @Autowired
    private RegistrationService service;

    @PutMapping("/{id}")
    public ResponseEntity<RegistrationUpdate> update(
            @RequestBody RegistrationUpdate dto, @PathVariable Long id) {
        RegistrationUpdate registration = this.service.update(dto, id);
        return ResponseEntity.ok(registration);
    }

}
