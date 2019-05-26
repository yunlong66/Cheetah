package com.cheetah.springboot.dao;

import com.cheetah.springboot.pojo.Category2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category2,Integer> {
}
