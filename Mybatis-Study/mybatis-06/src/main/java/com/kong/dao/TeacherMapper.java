package com.kong.dao;


import com.kong.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {

    @Select("select * from teacher where id = #{id}")
    Teacher getTeacher(@Param("id") int id);

}
