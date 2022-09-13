package com.example.json.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectMapperController {

    @GetMapping("/convert")
    public String convert() throws JsonProcessingException {

        User user = new User();
        user.setId(12);
        user.setName("Rodgers");
        user.setContactEmail("ar12@test.com");

        ObjectMapper objectMapper = new ObjectMapper();

        //java object -> json
        String jsonResult = objectMapper.writeValueAsString(user);
        System.out.println("json value is : " + jsonResult);

        //json -> java object
        String json = "{\"id\":2," +
                "\"name\":\"Ryan\"," +
                "\"age\":37," +
                "\"contact_email\":\"MR2@test.com\"}";

        User userResult = objectMapper.readValue(json, User.class);

        System.out.println("User's id is : " + userResult.getId());
        System.out.println("User's name is : " + userResult.getName());
        System.out.println("User's contact email is : " + userResult.getContactEmail());

        return "convert success";
    }

}
