package spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.convert.ConversionService;


import org.springframework.http.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@Configuration
@ComponentScan
@Component
@Service
@Repository
@RestController
@ControllerAdvice

public class Anoo {

    @Autowired
    public ConversionService conversionService;

    @PostMapping(value = "/content", produces = MediaType.APPLICATION_JSON_VALUE)
    public String ghsdfjas(){

        return "safdsaf";
    }

    @GetMapping(value = "/content", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public String fdghsgh(){

        return "safdsaf";
    }



}
