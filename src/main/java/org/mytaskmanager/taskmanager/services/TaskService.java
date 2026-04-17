package org.mytaskmanager.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.mytaskmanager.taskmanager.Status;
import org.mytaskmanager.taskmanager.model.Task;
import org.mytaskmanager.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TaskService {
    private final TaskRepository taskRepositor;


    public Task create(@RequestBody Task task) {
        return taskRepositor.save(task);
    }


    public List<Task> getAllTasks() {
        List<Task> checkingTask = taskRepositor.findAll();
        for (int i = 0; i < checkingTask.size(); i++) {
            if (checkingTask.get(i).getStatus() == Status.DONE) continue;
            if (checkingTask.get(i).getDate() == null)continue;
            boolean changed = false;
            if (LocalDate.now().isAfter(checkingTask.get(i).getDate())) {
                checkingTask.get(i).setStatus(Status.EXPIRED);
                changed = true;
            } else if (checkingTask.get(i).getTime() != null && LocalDate.now().isEqual(checkingTask.get(i).getDate()) && LocalTime.now().isAfter(checkingTask.get(i).getTime()) ) {
                checkingTask.get(i).setStatus(Status.EXPIRED);
                changed = true;
            }
            if (changed) {
                taskRepositor.save(checkingTask.get(i));
            }
        }
        return checkingTask;
    }



    public Task getById(Long id) {
        return taskRepositor.findById(id).orElse(null);
    }

    public void deleteTask(Long id) {
        taskRepositor.deleteById(id);
    }

    public Task update(Long id, Task task) {
        if (taskRepositor.existsById(id)) {
            task.setId(id);
            return taskRepositor.save(task);


        }return null;
    }

    public void doTheTask(Long id) {
       Task task = getById(id);
       if(task != null){
           task.setStatus(Status.DONE);
           taskRepositor.save(task);
       }
    }
}
