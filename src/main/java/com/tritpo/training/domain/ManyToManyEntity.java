package com.tritpo.training.domain;


public class ManyToManyEntity implements BaseEntity {

    private int id;
       private int firstID;
       private int secondID;
       private Role role;

    @Override
    public int getId() {
        return id;
    }

    public ManyToManyEntity(Builder builder) {
        this.id = builder.id;
        this.firstID = builder.firstID;
        this.secondID = builder.secondID;
        this.role = builder.role;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getFirstID() {
        return firstID;
    }

    public void setFirstID(int firstID) {
        this.firstID = firstID;
    }

    public int getSecondID() {
        return secondID;
    }

    public void setSecondID(int secondID) {
        this.secondID = secondID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static class Builder {
        private int id;
        private int firstID;
        private int secondID;
        private Role role;

        public static ManyToManyEntity.Builder newInstance() {
            return new ManyToManyEntity.Builder();
        }
       private Builder() {}
       public Builder setId(int id) {
            this.id = id;
            return this;
       }
        public Builder setFirstId(int id) {
            this.firstID = id;
            return this;
       }
        public Builder setSecondId(int id) {
            this.secondID = id;
            return this;
       }
       public Builder setRole(Role role) {
            this.role = role;
            return this;
       }
       public ManyToManyEntity build() {
            return new ManyToManyEntity(this);
       }
    }

    @Override
    public String toString() {
        return "ManyToManyEntity{" +
                "id=" + id +
                ", firstID=" + firstID +
                ", secondID=" + secondID +
                ", role=" + role +
                '}';
    }
}
