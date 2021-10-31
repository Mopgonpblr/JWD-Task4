package by.epamtc.HacakConstantine.task4.Bean;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private Role role;

    public User() {
    }

    public User(int id, String login, String password, String name, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass())
            return false;
        User u = (User) obj;
        if (this.id != u.id) return false;
        if (!this.login.equals(u.login)) return false;
        if (!this.password.equals(u.password)) return false;
        if (!this.name.equals(u.name)) return false;
        if (this.role != u.role) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.id + " " + this.login + " " + this.password + " " + this.name + " " + this.role;
    }

    @Override

    public int hashCode() {
        return (int) 31 * this.id
                + (this.login == null ? 0 : this.login.hashCode())
                + (this.password == null ? 0 : this.password.hashCode())
                + (this.name == null ? 0 : this.name.hashCode())
                + (this.role == null ? 0 : this.role.hashCode());
    }


}

