package cn.f33v.chapter3swagger2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2021/03/23 9:24
 * @Created by Administrator
 */
@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping(value = "/users")
public class UserController {
    //创建线程安全map,模拟users信息存储
    static Map<Long,User> userMap= Collections.synchronizedMap(new HashMap<>());

    /**
     * 处理"/users/"的GET请求,用来获取用户列表
     * @return 用户列表
     */
    @GetMapping("/")
    @ApiOperation("获取用户列表接口")
    @ApiImplicitParams({
    })
    public List<User> getUserList(){
        //可以通过@RequestParam从严中传递参数进行查询条件或翻页信息
        return new ArrayList<>(userMap.values());
    }

    /**
     * 处理"/users/"POST请求,用来创建User
     * @param user 用户
     * @return 信息
     */
    @ApiOperation( "添加用户的接口")
    @ApiImplicitParam(name = "user",value = "用户",defaultValue = "null")
    @PostMapping("/")
    public String postUser(@RequestBody User user){
        //@RequestBody注解用来绑定通过http请求中application/json类型上传的数据
        userMap.put(user.getId(),user);
        return "success";
    }

    /**
     * 处理"/users/{id}"的GET请求,用来获取url中id值的User信息
     * @param id id
     * @return user
     */
    @ApiOperation("根据id查找用户的接口")
    @ApiImplicitParam(name = "id",value = "用户id",defaultValue = "0")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        //url中的id可以通过@PathVariable绑定到函数的参数中
        return userMap.get(id);
    }

    /**
     * 处理"/users/{id}"的PUT请求,用来更新User信息
     * @param id id
     * @param user user
     * @return 信息
     */
    @ApiOperation("根据id更新用户的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",defaultValue = "0"),
            @ApiImplicitParam(name = "user",value = "用户",defaultValue = "null")
    })
    @PutMapping("/{id}")
    public String putUser(@PathVariable Long id,@RequestBody User user){
        User user1=userMap.get(id);
        user1.setName(user.getName());
        user1.setAge(user.getAge());
        userMap.put(id,user1);
        return "success";
    }

    /**
     * 处理"/users/{id}"的DELETE请求,用来删除User
     * @param id
     * @return
     */
    @ApiOperation("根据用户id删除用户接口")
    @ApiImplicitParam(name = "id",value = "用户id",defaultValue = "0")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userMap.remove(id);
        return "success";
    }
}
