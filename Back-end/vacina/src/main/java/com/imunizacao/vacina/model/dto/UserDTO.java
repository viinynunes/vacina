package com.imunizacao.vacina.model.dto;

import com.imunizacao.vacina.model.entities.User;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDTO extends RepresentationModel<UserDTO> implements Serializable {

    private Long id;
    private String fullName;
    private String userName;
    private String password;

    private List<String> roles = new ArrayList<>();

    public UserDTO(){}

    public UserDTO(Long id, String fullName, String userName, String password){
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }

    public UserDTO(User user){
        id = user.getId();
        fullName = user.getFullName();
        userName = user.getUserName();
        password = user.getPassword();

        roles = user.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
