package com.tritpo.training.domain;


public class User implements BaseEntity{

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String email;
    private boolean banned;
    private Role role;

    public User() {
    }

    public User(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.secondName = builder.secondName;
        this.email = builder.email;
        this.banned = builder.banned;
        this.role = builder.role;
    }

    @Override
    public int getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static class Builder {
        private int id;
        private String login;
        private String password;
        private String firstName;
        private String secondName;
        private String email;
        private boolean banned;
        private Role role;

        public static User.Builder newInstance() {
            return  new User.Builder();
        }
        private Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setIsBanned(boolean isBanned) {
            this.banned = isBanned;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }
}
