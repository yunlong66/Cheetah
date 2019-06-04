package tmall.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tmall.bean.Category;
import tmall.bean.Property;
import tmall.dao.CategoryDAO;
import tmall.util.Page;

public class PropertyServlet extends BaseBackServlet{
    public String add(HttpServletRequest request,HttpServletResponse response,Page page){
        //属性对应分类
        int cid = Integer.parseInt(request.getParameter("cid"));
        Category c = categoryDAO.get(cid);
        String name = request.getParameter("name");
        Property p =new Property();
        p.setName(name);
        p.setCategory(c);
        propertyDAO.add(p);

        return "@admin_property_list?cid=" + cid;



    }

    public String list(HttpServletRequest request,HttpServletResponse response,Page page){
        int cid = Integer.parseInt(request.getParameter("cid"));
        Category c = categoryDAO.get(cid);
        List<Property> ps = propertyDAO.list(cid, page.getStart(), page.getCount());
        int total = propertyDAO.getTotal(cid);
        page.setTotal(total);
        //因为属性分页都是基于当前分类下的分页，所以分页的时候需要传递这个cid
        page.setParam("&cid="+c.getId());

        request.setAttribute("ps", ps);
        request.setAttribute("c", c);
        request.setAttribute("page", page);

        return "admin_property_list?cid=" + cid;
    }

    public String edit(HttpServletRequest request, HttpServletResponse response, Page page){
        int id =Integer.parseInt(request.getParameter("id"));
        Property p = propertyDAO.get(id);
        request.setAttribute("p", p);
        //直接服务端跳转
        return "admin/editProperty.jsp";

    }

    public String update(HttpServletRequest request,HttpServletResponse response,Page page){
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        int cid = Integer.parseInt(request.getParameter("cid"));
        Category c = categoryDAO.get(cid);

        Property p = new Property();
        p.setCategory(c);
        p.setId(id);
        p.setName(name);
        propertyDAO.update(p);

        return "@admin_property_list?cid="+cid;
    }

    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id = Integer.parseInt(request.getParameter("id"));
        Property p = propertyDAO.get(id);
        propertyDAO.delete(id);
        return "@admin_property_list?cid="+p.getCategory().getId();
    }
}
