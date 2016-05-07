package io.pivotal.hackday.web;

        import io.pivotal.hackday.ccapi.AppEventService;
        import io.pivotal.hackday.ccapi.AppService;
        import io.pivotal.hackday.domain.*;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;

        import java.util.ArrayList;
        import java.util.Map;

/**
 * Created by pivotal on 5/6/16.
 */

@Controller
public class HomeController {

    @Autowired
    AppService appService;

    @Autowired
    AppEventService appEventService;

    @Autowired
    CloudFoundryInfo cloudFoundryInfo;

    @Autowired
    OpsManagerInfo opsManagerInfo;

    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        model.put("opsManInfo", opsManagerInfo);
        model.put("cloudfoundryInfo", cloudFoundryInfo);
        model.put("apps", appService.getAppCatalog());
        model.put("events", appEventService.getAppEvents());
        return "home";
    }
}
