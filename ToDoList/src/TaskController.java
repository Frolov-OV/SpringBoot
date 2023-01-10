package main;

import main.Model.Tasks;
import main.Model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @GetMapping("/tasks/")
    public List<Tasks> tasks(){
        Iterable<Tasks> tasksIterable = toDoListRepository.findAll();
        ArrayList<Tasks> taskArray = new ArrayList<>();
        for (Tasks task : tasksIterable)
            taskArray.add(task);
        return taskArray;
    }
    @PostMapping("/tasks/")
    public int add(Tasks tasks) {
        Tasks newTask = toDoListRepository.save(tasks);
        return newTask.getId();
    }
    @GetMapping ("/tasks/{id}")
    public ResponseEntity get (@PathVariable int id){
        Optional<Tasks> tasksOptional = toDoListRepository.findById(id);
        return tasksOptional.map(tasks -> new ResponseEntity(tasks, HttpStatus.OK)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteId (Tasks task) {
        toDoListRepository.deleteById(task.getId());
        if (!toDoListRepository.existsById(task.getId())){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    @DeleteMapping("/tasks/")
    public void delAll (){
        toDoListRepository.deleteAll();
    }
    @PutMapping("/tasks/")
    public ResponseEntity editTask(Tasks task) {
        Tasks tasks = toDoListRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
