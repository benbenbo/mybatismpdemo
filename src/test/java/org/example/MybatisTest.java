package org.example;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.beans.Employee;
import org.example.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(JUnit4.class)
public class MybatisTest {

    private ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");

    private EmployeeMapper employeeMapper=applicationContext.getBean("employeeMapper",EmployeeMapper.class);

    public void method01() throws SQLException {
        DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testEntityWrapperSelect(){
        //我们需要分页查询tbl_employee表中，年龄在18~50之间性别为男且姓名为Tom的所有用户
        //注意：EntityWrapper使用的是列名
        /*List<Employee> employees = employeeMapper.selectPage(new Page<Employee>(1, 2),
                new EntityWrapper<Employee>().between("age", 18, 50)
                        .eq("gender", 1)
                        .eq("last_name", "Tom"));
        System.out.println(employees);*/

        //查询tbl_employee表中，性别为女且名字带有老师 或者 邮箱带有“a”
        /*List<Employee> employees = employeeMapper.selectList(new EntityWrapper<Employee>()
                .eq("gender", 0)
                .like("last_name", "老师")
                //.or() //
                .orNew()
                .like("email", "a")
        );
        System.out.println(employees);*/

        //查询性别为女的，根据age进行排序，进行分页
        /*List<Employee> employees = employeeMapper.selectList(new EntityWrapper<Employee>()
                .eq("gender", 0)
                .orderBy("age")
                .last("desc limit 1,3"));
        System.out.println(employees);*/

        List<Employee> list = employeeMapper.selectPage(new Page<Employee>(1, 2), Condition.create()
                .eq("last_name", "Tom")
                .eq("gender",1)
                .between("age",18,50));
        System.out.println(list);
    }
    public void testEntityWrapperUpdate(){
        //对名字为Tom且age为44的记录进行修改为
        //名字="苍老师"，性别为女，email为cls@sina.com
        Employee employee = new Employee();
        employee.setLastName("苍老师");
        employee.setEmail("cls@sina.com");
        employee.setGender(0);

        Integer update = employeeMapper.update(employee, new EntityWrapper<Employee>()
                .eq("last_name", "Tom")
                .eq("age", 44));
        System.out.println("成功修改"+update+"条数据");
    }

    public void testEntityWrapperDelete(){
        //删除名字为Tom且年龄为22的记录
        Integer delete = employeeMapper.delete(new EntityWrapper<Employee>()
                .eq("last_name", "Tom")
                .eq("age", 22));
        System.out.println("成功修改"+delete+"条数据");
    }

    /**
     * 通用的删除操作
     */
    public void testCommonDelete(){
        //根据Id进行删除
        Integer i = employeeMapper.deleteById(12);
        System.out.println("成功删除"+i+"条记录");
        //根据条件进行删除
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("last_name","Mp");
//        hashMap.put("email","mp@atguigu.com");
//        Integer i = employeeMapper.deleteByMap(hashMap);
//        System.out.println("成功删除"+i+"条记录");
        //根据多个id删除记录
//        Integer i = employeeMapper.deleteBatchIds(Arrays.asList(3,4,5));
//        System.out.println("成功删除"+i+"条记录");
    }


    /**
     * 通用的查询操作
     */
    public void testCommonSelect(){
        //1.通过id查询
//        Employee employee = employeeMapper.selectById(7);
//        System.out.println(employee);
        //2.通过多个列进行查询id+lastName
//        Employee employee=new Employee();
//        employee.setId(7);
//        employee.setLastName("小泽老师");
//        employee.setGender(0);
//        Employee employee1 = employeeMapper.selectOne(employee);
//        System.out.println(employee1);
        //3.通过多个id查询
//        List<Integer> idList=new ArrayList<>();
//        idList.add(4);
//        idList.add(5);
//        idList.add(6);
//        idList.add(7);
//        List<Employee> employees = employeeMapper.selectBatchIds(idList);
//        System.out.println(employees);
        //4.通过Map封装条件查询
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("last_name","Tom");//这个是使用列名
//        hashMap.put("gender",1);
//        List<Employee> employees = employeeMapper.selectByMap(hashMap);
//        System.out.println(employees);

        //5.分页查询,
        //Page的T是实体类，这里是Employee
        List<Employee> employees = employeeMapper.selectPage(new Page<>(2, 2), null);
        System.out.println(employees);


    }


    /**
     * 通用更新操作
     */
    public void testCommonUpdate(){
        Employee employee=new Employee();
        employee.setId(7);
        employee.setLastName("小泽老师");
        employee.setEmail("xiaoze@sina.com");
        employee.setGender(0);
//        Integer i = employeeMapper.updateById(employee);
        Integer i = employeeMapper.updateAllColumnById(employee);
        System.out.println("成功修改"+i+"条记录");
    }


    public void testCommonInsert(){
        Employee employee = new Employee();
        employee.setLastName("Mp");
        employee.setGender(1);
//        employee.setAge(22);
//        employee.setEmail(" mp@atguigu.com");
        //插入到数据库中
//        int i = employeeMapper.insert(employee);
        Integer i = employeeMapper.insertAllColumn(employee);
        System.out.println("成功修改"+i+"条记录");

        //获取当前记录在数据库中的主键
        System.out.println("当前插入记录主键："+employee.getId());
    }
}
