package uy.com.elsubonline.web.admin;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.ICategoryService;

@ManagedBean
@RequestScoped
public class Category implements Serializable {

    private static final Logger logger = Logger.getLogger(Category.class);

    private String category_name;

    @EJB
    private ICategoryService service;

    /**
     * @return the category_name
     */
    public String getCategory_name() {
        return category_name;
    }

    /**
     * @param category_name the category_name to set
     */
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String create() {
        logger.info("Trying to create category: ");
        service.create(category_name);
        return "home";
    }

}
