package com.cheetah.springboot.service;

import com.cheetah.springboot.pojo.Category;
import com.cheetah.springboot.util.Page4Navigator;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    public Page4Navigator<Category> list(Pageable pageable);

    public void save(Category category);

    public void delete(int id);

    public Category get(int id);
}