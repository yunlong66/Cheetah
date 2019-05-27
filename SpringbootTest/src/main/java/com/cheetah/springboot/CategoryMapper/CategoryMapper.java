package com.cheetah.springboot.CategoryMapper;

import com.cheetah.springboot.pojo.Category;
import com.cheetah.springboot.pojo.Category2;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {

    @Select("select * from category_ ")
    List<Category2> findAll();

    @Insert(" insert into category_ ( name ) values (#{name}) ")
    public int save(Category2 category);

    @Delete(" delete from category_ where id= #{id} ")
    public void delete(int id);

    @Select("select * from category_ where id= #{id} ")
    public Category2 get(int id);

    @Update("update category_ set name=#{name} where id=#{id} ")
    public int update(Category2 category);
}
