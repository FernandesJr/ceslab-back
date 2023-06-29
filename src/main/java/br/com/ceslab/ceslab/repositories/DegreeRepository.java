package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DegreeRepository extends JpaRepository<Degree, Long> {
}
