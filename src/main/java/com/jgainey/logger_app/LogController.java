package com.jgainey.logger_app;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogController {



    @RequestMapping(method = RequestMethod.GET, value = "/log", produces = "application/json")
    public ResponseEntity<String> log(@RequestParam(value = "count", defaultValue = "100") int count,
                                      @RequestParam(value = "type", defaultValue = "info") String typeIn,
                                      @RequestParam(value = "message",
                                              defaultValue = "logger_app_message iteration number: ") String messageIn){

        //the type of log to do
        Utils.LOGTYPE type;


        switch (typeIn){
            case "info":
                type = Utils.LOGTYPE.INFO;
                break;
            case "warning":
                type = Utils.LOGTYPE.WARNING;
                break;
            case "error":
                type = Utils.LOGTYPE.ERROR;
                break;
            default:
                return new ResponseEntity<>("Expected the type" +
                        "of log to be info,warning, or error but got " + typeIn, HttpStatus.OK);
        }

        for (int i = 0; i< count; i++){
            Utils.log(type, messageIn + i);

        }

        return new ResponseEntity<>("Successfully logged " + "messages", HttpStatus.OK);
    }

}
