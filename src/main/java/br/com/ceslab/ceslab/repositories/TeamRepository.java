package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByName(String name);
}
