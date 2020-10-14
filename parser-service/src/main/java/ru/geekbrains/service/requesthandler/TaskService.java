package ru.geekbrains.service.requesthandler;

import lombok.extern.java.Log;
<<<<<<< HEAD
=======
import lombok.extern.slf4j.Slf4j;
>>>>>>> upstream/develop
import org.springframework.stereotype.Service;
import ru.geekbrains.model.Task;
import java.util.LinkedList;
import java.util.Queue;

<<<<<<< HEAD
@Log
@Service
public class TaskService {
    private Queue<Task> tasks = new LinkedList<>();
=======
@Slf4j
@Service
public class TaskService {
    private volatile Queue<Task> tasks = new LinkedList<>();
>>>>>>> upstream/develop

    public boolean add(Task task){
        if (!tasks.contains(task)) {
            log.info("задача добавлена в очередь");
            tasks.add(task);
            return true;
        }
        log.info("такая задача уже в очереди");
        return false;
    }

    //отдает объект, но не удаляет его из очереди
    //нужно для того, чтобы задачи не дублировались, пока объект находится в работе
    public Task peek(){
        log.info("задача передана в работу");
        return tasks.peek();
    }

    //обязательно удалить элемент после выполнения
    public void poll(){
        log.info("задача удалена");
        tasks.poll();
    }

    public boolean isEmpty(){
<<<<<<< HEAD
        if(tasks.size() > 0){
            return false;
        } else {
            return true;
        }
=======
        return tasks.isEmpty();
>>>>>>> upstream/develop
    }


}
