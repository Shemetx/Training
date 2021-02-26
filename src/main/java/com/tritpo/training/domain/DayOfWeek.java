package com.tritpo.training.domain;

import com.tritpo.training.exception.UnknownEntityException;

import java.util.Arrays;


public enum DayOfWeek implements BaseEntity{
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private int id;

    DayOfWeek(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        DayOfWeek dayOfWeek = resolveById(id);
        String lower = dayOfWeek.toString().toLowerCase();
        return upperCaseFirst(lower);
    }

    public static DayOfWeek resolveById(int id) {
        DayOfWeek[] dayOfWeek = DayOfWeek.values();
        return Arrays.stream(dayOfWeek)
                .filter(dayOfWeek1 -> dayOfWeek1.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UnknownEntityException("Role not found"));
    }

    private static String upperCaseFirst(String value) {

        // Convert String to char array.
        char[] array = value.toCharArray();
        // Modify first element in array.
        array[0] = Character.toUpperCase(array[0]);
        // Return string.
        return new String(array);
    }

}
