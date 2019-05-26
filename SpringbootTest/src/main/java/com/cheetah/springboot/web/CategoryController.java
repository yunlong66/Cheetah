package com.cheetah.springboot.web;

import com.cheetah.springboot.dao.CategoryDAO;
import com.cheetah.springboot.pojo.Category2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cheetah.springboot.CategoryMapper.CategoryMapper;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import java.util.List;


@Controller
public class CategoryController {
    @Autowired
    //CategoryMapper categoryMapper;
    CategoryDAO categoryDAO;

//    @RequestMapping("/listCategory")
//    public String listCategory(Model m) throws Exception {
//        List<Category2> cs=categoryDAO.findAll();
// //       List<Category> cs=categoryMapper.findAll();
//
//        m.addAttribute("cs", cs);
//
//        return "listCategory";
//    }

    @RequestMapping("/listCategory")
    public String listCategory(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Category2> page =categoryDAO.findAll(pageable);
        m.addAttribute("page", page);
        return "listCategory";
    }

    @RequestMapping("/addCategory")
    public String addCategory(Category2 c) throws Exception {
        categoryDAO.save(c);
        return "redirect:listCategory";
    }
    @RequestMapping("/deleteCategory")
    public String deleteCategory(Category2 c) throws Exception {
        categoryDAO.delete(c);
        return "redirect:listCategory";
    }
    @RequestMapping("/updateCategory")
    public String updateCategory(Category2 c) throws Exception {
        categoryDAO.save(c);
        return "redirect:listCategory";
    }
    @RequestMapping("/editCategory")
    public String editCategory(int id,Model m) throws Exception {
        Category2 c= categoryDAO.getOne(id);
        m.addAttribute("c", c);
        return "editCategory";
    }

}
