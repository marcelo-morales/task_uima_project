package com.cs250.joanne.myfragments;

import java.lang.String;
import java.time.LocalDate;

/**
 * Holds data for one task
 */
public class Task {
    private String task_name;
    private String deadline;
    private String category;


    public Task(String task_name, String deadline, String category) {
        this.task_name = task_name;
        this.deadline = deadline;
        this.category = category;
    }

    public String getWhat() {
        return task_name + "\n" + deadline + "\n" + category;
    }

    public String getName() {
        return task_name;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getCategory() {
        return category;
    }
}
