package WebAppJSF.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@SuppressWarnings("deprecation")
@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {

    public String viewIndex() {
        return "index";
    }

    public String viewCalculator() {
        return "calculator";
    }
}
