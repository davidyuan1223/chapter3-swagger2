package cn.f33v.chapter3swagger2;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class Chapter3Swagger2ApplicationTests {
    private MockMvc mockMvc;
    @Before
    public void setUP(){
        stringContainsInOrder("init");
    }
    @Test
    void testUserController() throws Exception {
        mockMvc= MockMvcBuilders.standaloneSetup(new UserController()).build();
        //测试UserController
        RequestBuilder requestBuilder;
        //1. get查一下user列表,应该为空
        requestBuilder=get("/users/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
        //2. post提交一个user
        requestBuilder=post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"test\",\"age\":20}");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));
        //3. get获取user列表
        requestBuilder = get("/users/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"test\",\"age\":20}]")));
        //4. put修改id为1的user
        requestBuilder=put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test1\",\"age\":30}");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));
        //5. get一个id为1的user
        requestBuilder=get("/users/1");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"test1\",\"age\":30}")));
        //6. del删除id为1的user
        requestBuilder=delete("/users/1");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));
        //7. get一下user列表,应该为空
        requestBuilder=get("/users/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }
    @Test
    void contextLoads() {
    }

}
