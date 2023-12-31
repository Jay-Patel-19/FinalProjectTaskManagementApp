package com.example.finalprojecttaskmanagementapp;

import java.util.Date;

public class TaskModel {
    String task_id, task_name, task_description, priority, location;
    boolean isCompleted;
    Date due_date;

    public TaskModel() {
    }

    public TaskModel(String task_id, String task_name, String task_description, String priority, String location, boolean isCompleted, Date due_date) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_description = task_description;
        this.priority = priority;
        this.location = location;
        this.isCompleted = isCompleted;
        this.due_date = due_date;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }
}
