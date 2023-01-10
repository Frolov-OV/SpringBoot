package main;

import main.Model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
public class DefaultController {

    @Autowired
    private ToDoListRepository toDoListRepository;


    @RequestMapping("/")
    public String index (){
        String str = "Сегодня ";

        return  str + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, HH:mm:ss",
                        new Locale("ru")));
    }

}
