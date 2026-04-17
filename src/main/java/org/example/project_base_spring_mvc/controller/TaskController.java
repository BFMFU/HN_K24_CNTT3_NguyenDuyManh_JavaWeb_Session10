package org.example.project_base_spring_mvc.controller;

import org.example.project_base_spring_mvc.model.TaskItem;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    
    private static final List<TaskItem> taskList = new ArrayList<>();
    
    static {
        taskList.add(new TaskItem("T01", "Học JAVA WEB", LocalDate.now().plusDays(5), "HIGH"));
        taskList.add(new TaskItem("T02", "Học Session", LocalDate.now().plusDays(10), "HIGH"));
        taskList.add(new TaskItem("T03", "Học Spring Boot", LocalDate.now().plusDays(7), "MEDIUM"));
        taskList.add(new TaskItem("T04", "Học Transaction", LocalDate.now().plusDays(15), "LOW"));
        taskList.add(new TaskItem("T05", "Code Review", LocalDate.now().plusDays(3), "MEDIUM"));
    }
    
    @GetMapping("")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskList);
        return "task-list";
    }
    
    @GetMapping("/form")
    public String showForm(Model model) {
        if (!model.containsAttribute("taskItem")) {
            model.addAttribute("taskItem", new TaskItem());
        }
        return "task-form";
    }
    
    @PostMapping("")
    public String saveTask(@Valid TaskItem taskItem, BindingResult bindingResult, 
                          Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("taskItem", taskItem);
            return "task-form";
        }

        if (taskItem.getId() == null || taskItem.getId().isEmpty()) {
            taskItem.setId(UUID.randomUUID().toString());
        }

        taskList.add(taskItem);

        redirectAttributes.addFlashAttribute("message", "Task added successfully!");
        return "redirect:/tasks";
    }
}
