package org.example.beans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * JavaBean
 *
 * 定义JavaBean时，成员变量所使用的类型
 *  因为每个基本类型都有一个默认值：
 *      int==>0
 *      boolean==>false
 *  没办法判断是否为空
 *  因此建议使用包装类型
 *
 */
//@TableName("mp.tbl_employee")
public class Employee {
    /**
     * value如果和数据表中的一致，可以不指定
     * type：主键生成策略
     */
//    @TableId(value="id",type = IdType.AUTO)
    private Integer id; //
    @TableField(value="last_name")
    private String lastName;
    private String email;
    private Integer gender;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
