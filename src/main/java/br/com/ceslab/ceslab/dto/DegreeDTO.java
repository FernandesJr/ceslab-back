package br.com.ceslab.ceslab.dto;

import br.com.ceslab.ceslab.entities.Degree;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DegreeDTO implements Serializable {

    private Long id;
    private String code;
    private LocalDateTime generationDate;
    private StudentDTO studentDTO;
    private TeamDTO teamDTO;

    public DegreeDTO(){}

    public DegreeDTO(Long id, String code, LocalDateTime generationDate, StudentDTO studentDTO, TeamDTO teamDTO) {
        this.id = id;
        this.code = code;
        this.generationDate = generationDate;
        this.studentDTO = studentDTO;
        this.teamDTO = teamDTO;
    }

    public DegreeDTO(Degree entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.generationDate = entity.getGenerationDate();
        this.studentDTO = new StudentDTO(entity.getStudent());
        this.teamDTO = new TeamDTO(entity.getTeam());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(LocalDateTime generationDate) {
        this.generationDate = generationDate;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public TeamDTO getTeamDTO() {
        return teamDTO;
    }

    public void setTeamDTO(TeamDTO teamDTO) {
        this.teamDTO = teamDTO;
    }
}
