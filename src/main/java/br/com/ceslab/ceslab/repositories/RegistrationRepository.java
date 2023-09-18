package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
