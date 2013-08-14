package uy.com.elsubonline.web;

import com.sun.faces.util.MessageFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.dtos.UserDto;
import uy.com.elsubonline.web.user.Login;

public class AccessControlPhaseListener implements PhaseListener {

    private static final Logger logger = Logger.getLogger(AccessControlPhaseListener.class);

    @Override
    public void afterPhase(PhaseEvent event) {

        logger.info("AccessControlPhaseListener.afterPhase called");

        FacesContext context = event.getFacesContext();
        Login sessionBean = (Login)context.getApplication().evaluateExpressionGet(context, "#{login}", Login.class);

        if (sessionBean == null) {
            logger.error("Could not obtain instance of sessionBean");
            return;
        }

        String viewId = context.getViewRoot().getViewId();
        logger.info("Processing: " + viewId);

        if (viewId.startsWith("/user/")) {
            UserDto activeUser = sessionBean.getActiveUser();
            if (activeUser == null) {
                addError(context, "access.loginrequired");
                context.getApplication().getNavigationHandler().handleNavigation(context, null, "home");
            }
        }

        if (viewId.startsWith("/admin/")) {
            UserDto activeUser = sessionBean.getActiveUser();
            if (activeUser == null || !activeUser.isAdministrator()) {
                addError(context, "access.adminrequired");
                context.getApplication().getNavigationHandler().handleNavigation(context, null, "home");
            }
        }

    }

    private void addError(FacesContext context, String key) {
        FacesMessage fMessage = MessageFactory.getMessage(key);
        if (fMessage != null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            fMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(null, fMessage);
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        logger.info("AccessControlPhaseListener.beforePhase called");
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
