package br.com.ceslab.ceslab.dto.user;

import br.com.ceslab.ceslab.entities.User;

public class UserUpdateDTO extends User {

    private String newPassword;
    private String confirmPassword;

    public UserUpdateDTO() {}

    public UserUpdateDTO(User entity) {
        this.setId(entity.getId());
        this.setEmail(entity.getEmail());
        this.setName(entity.getName());
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


}
