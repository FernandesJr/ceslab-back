package br.com.ceslab.ceslab.repositories;

import br.com.ceslab.ceslab.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
