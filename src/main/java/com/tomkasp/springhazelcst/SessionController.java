package com.tomkasp.springhazelcst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
public class SessionController {

    @Autowired
    HazelcastSessionProvider hazelcastSessionProvider;


    @GetMapping("/sessionid")
    public String getSessionId(){
        return hazelcastSessionProvider.getSession(hazelcastSessionProvider.sessionKey);
    }

    @GetMapping("/sessionid/{id}")
    public void setSessionId(@PathVariable String id){
        hazelcastSessionProvider.setSession(id);
    }
}
