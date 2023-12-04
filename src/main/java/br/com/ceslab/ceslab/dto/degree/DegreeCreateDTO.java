package br.com.ceslab.ceslab.dto.degree;

import java.io.Serializable;

public class DegreeCreateDTO implements Serializable {

    private Long studentId;
    private Long teamId;

    public DegreeCreateDTO() {}

    public DegreeCreateDTO(Long studentId, Long teamId) {
        this.studentId = studentId;
        this.teamId = teamId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
