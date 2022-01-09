package com.example.tickets.controller;


import com.example.tickets.entity.Customer;
import com.example.tickets.entity.Event;
import com.example.tickets.entity.Sale;
import com.example.tickets.security.CurrentUser;
import com.example.tickets.security.jwt.JwtUser;
import com.example.tickets.security.model.User;
import com.example.tickets.security.serviseSecurity.UserService;
import com.example.tickets.service.EventService;
import com.example.tickets.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;




@Validated
@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService service;

    @Secured({"ROLE_MANAGER"})
    @GetMapping("/customer/{customerId}")
    public List<Sale> getSaleByCustomer (@PathVariable(value = "customerId") Long customerId){
        return service.getAllSalesByCustomerId(customerId);
    }

    @Secured({"ROLE_USER"})
    @GetMapping("/event/{eventId}")
    public List<Sale> getSaleByEvent (@PathVariable(value = "eventId") Long eventId){
        return service.getAllSalesByEventId(eventId);
    }

    @Secured({"ROLE_USER"})
    @PostMapping("/customer/{customerId}/{eventId}")
    public Sale createSales2 (@PathVariable (value = "customerId") Long customerId,
                              @PathVariable (value = "eventId") Long eventId,
                              @Valid @RequestBody Sale sale) {
        return service.createSaleByCustomerIdAndEventId (customerId, eventId, sale);
    }

    @Secured({"ROLE_MANAGER"})
    @PostMapping("/customer/{eventId}")
    public Sale createSales5 (@AuthenticationPrincipal JwtUser user,
                              @PathVariable (value = "eventId") Long eventId,
                              @Valid @RequestBody Sale sale) {

        return service.createSaleByUserIdAndEventId (user.getId(), eventId, sale);
    }


}
