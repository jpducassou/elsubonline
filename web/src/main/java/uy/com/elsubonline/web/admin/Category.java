package uy.com.elsubonline.web.admin;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.ICategoryService;
import java.util.List;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import javax.faces.application.FacesMessage;

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

    public List<String> getList() {
        return service.list();
    }

    public void onEdit(RowEditEvent event) {
        String old_category_name = (String)event.getObject();
        logger.info("Updating " + old_category_name + " to " + category_name);
        service.update(old_category_name, category_name);
        FacesMessage msg = new FacesMessage("Category edited", (String)event.getObject());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
    }


    public String create() {
        logger.info("Trying to create category: " + category_name);
        service.create(category_name);
        return "home";
    }

}
