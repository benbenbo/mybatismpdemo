package org.example.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.example.beans.Employee;

/**
 * Mapper接口
 * 基于Mybatis去实现的话，在Mapper接口还需要编写CRUD
 * 还需要提供Mapper映射文件
 *
 * 基于Mp：继承BaseMapper<T>接口，T代表是当前Mapper所操作的实体类的类型
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
}
