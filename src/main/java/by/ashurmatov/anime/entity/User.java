package by.ashurmatov.anime.entity;

import by.ashurmatov.anime.entity.type.Status;
import by.ashurmatov.anime.entity.type.UserRole;

import java.util.Objects;

public class User extends AbstractEntity{
    private int id;
    private String email;
    private UserRole role;
    private String firstname;

    private String lastname;
    private String userName;
    private String password;
    private Status status;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email) && role == user.role && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, role, firstname, lastname, userName, password, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
