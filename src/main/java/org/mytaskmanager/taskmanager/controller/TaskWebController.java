package org.mytaskmanager.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.mytaskmanager.taskmanager.Status;
import org.mytaskmanager.taskmanager.model.Task;
import org.mytaskmanager.taskmanager.repository.TaskRepository;
import org.mytaskmanager.taskmanager.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/ui/tasks")
@RequiredArgsConstructor
public class TaskWebController {
    private final TaskService taskService;

    @GetMapping
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }

    @PostMapping
    public String createTask(Task task) {
        taskService.create(task);
        return "redirect:/ui/tasks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/ui/tasks";
    }

    @PostMapping("/{id}/done")
    public String doTheTask(@PathVariable Long id) {
        taskService.doTheTask(id);
        return "redirect:/ui/tasks";
    }

}
