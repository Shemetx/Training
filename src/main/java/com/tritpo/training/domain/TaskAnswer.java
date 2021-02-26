package com.tritpo.training.domain;


public class TaskAnswer implements BaseEntity{
    private int id;
    private String answer;
    private String comment;
    private TaskAnswerStatus status;
    private int userId;
    private int taskId;

    public TaskAnswer() {
    }

    public TaskAnswer(Builder builder) {
        this.id = builder.id;
        this.answer = builder.answer;
        this.comment = builder.comment;
        this.status = builder.status;
        this.userId = builder.userId;
        this.taskId = builder.taskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TaskAnswerStatus getStatus() {
        return status;
    }

    public void setStatus(TaskAnswerStatus status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public static class Builder {
        private int id;
        private String answer;
        private String comment;
        private TaskAnswerStatus status;
        private int userId;
        private int taskId;


        public static TaskAnswer.Builder newInstance() {
            return new TaskAnswer.Builder();
        }
        private Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setAnswer(String answer) {
            this.answer = answer;
            return this;
        }
        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }
        public Builder setStatus(TaskAnswerStatus status) {
            this.status = status;
            return this;
        }
        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setTaskId(int taskId) {
            this.taskId  = taskId;
            return this;
        }

        public TaskAnswer build() {
            return new TaskAnswer(this);
        }

    }

}
