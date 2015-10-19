package fr.emn.fil.reservation.dto;

/**
 * Created by Alexandre on 12/10/2015.
 */
public class UserDTO {

    private String username;

    public UserDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
