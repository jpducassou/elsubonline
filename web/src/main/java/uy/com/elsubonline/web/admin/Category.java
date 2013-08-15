package uy.com.elsubonline.web.admin;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import uy.com.elsubonline.api.ICategoryService;
import uy.com.elsubonline.api.exceptions.ServiceException;

@ManagedBean
@RequestScoped
public class Category implements Serializable {

    private static final Logger logger = Logger.getLogger(Category.class);

    private String category_name;

    @EJB
    private ICategoryService service;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<String> getList() {
        return service.list();
    }

    public void onEdit(RowEditEvent event) {

        String old_category_name = (String)event.getObject();

        logger.info("Updating " + old_category_name + " to " + category_name);

        FacesMessage msg;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");

        try {
            service.update(old_category_name, category_name);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("msg_category_updated"), category_name);
        } catch (ServiceException ex) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("err_category_aborted"), category_name);
        }

        facesContext.addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
    }


    public String create() {

        logger.info("Trying to create category: " + category_name);

        FacesMessage msg;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");

        try {
            service.create(category_name);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("msg_category_created"), category_name);
        } catch (ServiceException ex) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("err_category_aborted"), category_name);
        }

        facesContext.addMessage(null, msg);
        return "/index.xhtml";
    }

}
