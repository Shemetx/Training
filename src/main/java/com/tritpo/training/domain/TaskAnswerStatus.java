package com.tritpo.training.domain;

import com.tritpo.training.exception.UnknownEntityException;

import java.util.Arrays;


public enum TaskAnswerStatus implements BaseEntity{
    SUCCESS(1),
    OK(2),
    FAILED(3),
    EXPIRED(4),
    NOT_ANSWERED(5);

    private int id;

    TaskAnswerStatus(int id) {this.id = id;}


    public int getId() {
        return id;
    }

    public String getName() {
        TaskAnswerStatus status = resolveById(id);
        return status.toString();
    }

    public static TaskAnswerStatus resolveById(int id) {
        TaskAnswerStatus[] statuses = TaskAnswerStatus.values();
        return Arrays.stream(statuses)
                .filter(status -> status.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UnknownEntityException("Role not found"));
    }
}
