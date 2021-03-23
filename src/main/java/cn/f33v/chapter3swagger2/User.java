package cn.f33v.chapter3swagger2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname User
 * @Description TODO
 * @Date 2021/03/23 9:24
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class User {
    @ApiModelProperty(value = "用户id")
    private Long id;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "用户年龄")
    private Integer age;
}
