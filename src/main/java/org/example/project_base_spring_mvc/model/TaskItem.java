package org.example.project_base_spring_mvc.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class TaskItem {
    private String id;
    
    @NotBlank(message = "Title is required")
    @Size(min = 5, message = "Title must be at least 5 characters long")
    private String title;
    
    @NotNull(message = "Deadline is required")
    @Future(message = "Deadline must be a future date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    
    @NotBlank(message = "Priority is required")
    @Pattern(regexp = "^(HIGH|MEDIUM|LOW)$", message = "Priority must be HIGH, MEDIUM, or LOW")
    private String priority;
    
    public TaskItem() {
    }
    
    public TaskItem(String id, String title, LocalDate deadline, String priority) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.priority = priority;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public LocalDate getDeadline() {
        return deadline;
    }
    
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    
    public String getPriority() {
        return priority;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    @Override
    public String toString() {
        return "TaskItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", deadline=" + deadline +
                ", priority='" + priority + '\'' +
                '}';
    }
}
