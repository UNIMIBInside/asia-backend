package it.unimib.disco.asia.backend.controller;


import it.unimib.disco.asia.backend.model.customevent.CustomEventLogicRequest;
import it.unimib.disco.asia.backend.service.CustomEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomEvents {

    @Autowired
    private CustomEventsService customEventsService;

    @RequestMapping(value = "customevents/match", produces = "application/json", method = RequestMethod.POST)
    public String matchCustomEvents(@RequestBody List<CustomEventLogicRequest> lstLogic) {


        return customEventsService.retrieveIds(lstLogic).toString();

    }

    @RequestMapping(value = "customevents/select", produces = "application/json")
    public String queryCustomEvents(@RequestParam String ids,
                                    @RequestParam String propIds) {
        String[] eventIds = ids.split(",");
        String[] propertyIDs = propIds.split(",");

        return customEventsService.findByIds(eventIds, propertyIDs).toString();

    }

}
