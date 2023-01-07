package org.converter.swagger.api.controller;


import org.converter.swagger.api.service.ICApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/capi")
public class CApisController {

    @Autowired
    private ICApiService service;

    @PostMapping(consumes = "text/plain", produces = "*/*", path = "/cheader")
    public String capis(@RequestBody String yaml) {
        return service.createHeader(yaml).toHeaderString();
    }
}
