package com.tritpo.training.domain;

import java.time.LocalDateTime;


public class Task implements BaseEntity{


    private int id;
    private String title;
    private String description;
    private LocalDateTime deadLine;
    private int course_id;

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Task() {
    }

    public Task(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;;
        this.description = builder.description;
        this.deadLine = builder.deadLine;
        this.course_id = builder.course_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public static class Builder {
        private int id;
        private String title;
        private String description;
        private LocalDateTime deadLine;
        private int course_id;

        public static Task.Builder newInstance() {
            return  new Task.Builder();
        }
        private Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCourseId(int id) {
            this.course_id = id;
            return this;
        }
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDeadLine(LocalDateTime deadLine) {
            this.deadLine = deadLine;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                '}';
    }
}

