package org.example;

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

    /**
     * 通用的查询操作
     */
    @Test
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

        //5.分页查询


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
