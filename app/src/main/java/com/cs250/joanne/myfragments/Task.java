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
    private Boolean completed;


    public Task(String task_name, String deadline, String category) {
        this.task_name = task_name;
        this.deadline = deadline;
        this.category = category;
        completed = false;
    }

    public String getWhat() {
        return "Task: " + task_name + "\n" + "Due: " + deadline + "\n" + "Category: " + category;
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

    public void markComplete() {
        this.completed = true;
    }

    public  boolean checkCompletetion() {
        return this.completed;
    }
}
