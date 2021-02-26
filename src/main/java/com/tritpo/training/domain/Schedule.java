package com.tritpo.training.domain;

import java.time.LocalTime;


public class Schedule implements BaseEntity{

    private int id;
    private LocalTime startTime;
    private DayOfWeek dayOfWeek;

    public Schedule() {
    }

   public Schedule(Builder builder) {
        this.id = builder.id;
        this.startTime = builder.startTime;
        this.dayOfWeek = builder.dayOfWeek;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public static class Builder {
        private int id;
        private LocalTime startTime;
        private DayOfWeek dayOfWeek;

        public static Schedule.Builder newInstance() {return new Schedule.Builder();}
        private Builder() {}

        public Builder setId(int id) {
            this.id  = id;
            return this;
        }
        public Builder setStartTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }
        public Builder setDayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
            return this;
        }
        public Schedule build() {
            return new Schedule(this);
        }
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
