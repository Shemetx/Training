package com.tritpo.training.domain;

import java.time.LocalDate;
import java.util.Set;


public class Course implements BaseEntity{

    private int id;
    private String title;
    private String description;
    private LocalDate startDate;
    private Set<Schedule> scheduleSet;

    public Course() {
    }


    public Course(Builder builder) {
        this.id = builder.id;
        this.title  = builder.title;
        this.description = builder.description;
        this.startDate = builder.startDate;
        this.scheduleSet = builder.scheduleSet;
    }

    @Override
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Set<Schedule> getScheduleSet() {
        return scheduleSet;
    }

    public void setScheduleSet(Set<Schedule> scheduleSet) {
        this.scheduleSet = scheduleSet;
    }

    public static class Builder {

        private int id;
        private String title;
        private String description;
        private LocalDate startDate;
        private Set<Schedule> scheduleSet;


        public static Builder newInstance() {
            return  new Builder();
        }
        private Builder() {}
        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setTitle(String title) {
            this.title = title;
            return  this;
        }
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }
        public Builder setDate(LocalDate date) {
            this.startDate = date;
            return this;
        }
        public Builder setSchedule(Set<Schedule> schedule) {
            this.scheduleSet = schedule;
            return this;
        }

        public Course build() {
            return new Course(this);
        }

    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", scheduleSet=" + scheduleSet +
                '}';
    }
}
