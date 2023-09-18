package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.registration.RegistrationUpdate;
import br.com.ceslab.ceslab.entities.Registration;
import br.com.ceslab.ceslab.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationResource {

    @Autowired
    private RegistrationService service;

    @PutMapping
    public ResponseEntity<RegistrationUpdate> update(@RequestBody RegistrationUpdate dto) {
        RegistrationUpdate registration = this.service.update(dto);
        return ResponseEntity.ok(registration);
    }

}
