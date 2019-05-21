package com.cheetah.springboot.dao;

import com.cheetah.springboot.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {
}
