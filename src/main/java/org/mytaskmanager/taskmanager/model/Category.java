package org.mytaskmanager.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mytaskmanager.taskmanager.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private String name;
    private Status status;
}
