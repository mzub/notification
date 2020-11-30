package ru.geekbrains.requesthandler;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.model.Task;
import ru.geekbrains.service.requesthandler.TaskService;
import ru.geekbrains.utils.Converter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RequestHndlerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TaskService taskService;

    @Test
    @Order(1)
    public void test1() throws Exception {
        Task task = new Task("123", "Москва", "Россия");

//        mvc.perform(post("/")
//                .content(Converter.convertObjectToJsonBytes(task))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//                .andExpect(view().name("redirect:/brands"));
    }

    @Test
    @Order(2)
    public void test2() throws Exception {
//        Task task = new Task("123", "Москва", "Россия");
//
//        mvc.perform(post("/")
//                .content(Converter.convertObjectToJsonBytes(task))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
    }

    @Test
    @Order(3)
    public void test3() throws Exception {
//        Task task = new Task();
//
//        mvc.perform(post("/")
//                .content(Converter.convertObjectToJsonBytes(task))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
    }

    @Test
    public void test4() {
//        Task task = new Task("123", "Москва", "Россия");
//        taskService.add(task);
//        Assert.assertEquals(task, taskService.peek());
//        taskService.poll();
//        Assert.assertNull(taskService.peek());
    }



//    @Test
//    public void testCreateUser() throws Exception {
//
//        Task task = new Task("123", "Москва", "Россия");
//
//        mvc.perform(post("/api/create")
//                .accept(MediaType.APPLICATION_JSON)
////                .header("AUTH_TOKEN", TOKEN)
//                .content(Converter.convertObjectToJsonBytes(task)))
//                .andExpect(status().isCreated());
//    }
}
