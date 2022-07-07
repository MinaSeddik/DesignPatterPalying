package spring;

import org.apache.maven.model.Model;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.stream.Collectors;


@EnableWebMvc
@RestController
public class UserController {


    @RequestMapping(value = "/saveBasicUserInfo", method = RequestMethod.POST)
    public ResponseEntity<?> saveBasicUserInfo(@Valid @ModelAttribute("user") User user, BindingResult result,
                                               Errors errors /* inject if needed */, Model model /* Optional if needed*/) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result);
        }

//        MediaType.MULTIPART_FORM_DATA;
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        // calling service(s) for business logic

        return ResponseEntity.ok().build();
    }

}