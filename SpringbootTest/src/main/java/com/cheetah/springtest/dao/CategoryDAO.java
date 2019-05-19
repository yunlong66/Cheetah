package com.cheetah.springtest.dao;

import com.cheetah.springtest.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryDAO extends JpaRepository<Category,Integer>{

}
