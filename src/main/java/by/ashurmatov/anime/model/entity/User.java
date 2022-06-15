package by.ashurmatov.anime.model.entity;

import by.ashurmatov.anime.model.entity.enums.Role;

import java.util.Objects;

public class User extends AbstractEntity{
    private int id;
    private String email;
    private Role role;
    private String firstName;
    private String secondName;
    private String userName;

    public User() {
    }

    public User(int id, String email, Role role, String firstName, String secondName, String userName) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.firstName = firstName;
        this.secondName = secondName;
        this.userName = userName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email) && role == user.role && Objects.equals(firstName, user.firstName) && Objects.equals(secondName, user.secondName) && Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, role, firstName, secondName, userName);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
