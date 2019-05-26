package com.cheetah.springboot.CategoryMapper;

import com.cheetah.springboot.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {

    @Select("select * from category_ ")
    List<Category> findAll();
}
