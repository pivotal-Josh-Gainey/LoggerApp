package com.jgainey.logger_app;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


@Controller
public class LogController {

    AtomicBoolean blast = new AtomicBoolean(false);
    Random random = new Random();

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

    @RequestMapping(method = RequestMethod.POST, value = "/log2", produces = "application/json")
    public ResponseEntity<String> log2(@RequestParam(value = "count", defaultValue = "100") int count,
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

    @RequestMapping(method = RequestMethod.GET, value = "/blaston", produces = "application/json")
    public ResponseEntity<String> blaston(@RequestParam(value = "sleeptime", defaultValue = "50") int sleeptime,
                                          @RequestParam(value = "longstring", defaultValue = "false") boolean longtext){
        blastonAsync(sleeptime, longtext);
        return new ResponseEntity<>("Started", HttpStatus.OK);
    }

    public void blastonAsync(int sleeptime, boolean longtext) {
        new Thread(() -> {
            System.out.println("=== Testing " + Thread.currentThread().getName() + " ===");
            blast.set(true);
            while(blast.get()){
                try {
                    Thread.sleep(sleeptime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(longtext){
                    Utils.log(Utils.LOGTYPE.INFO,LongString.text);
                }else{
                    Utils.log(Utils.LOGTYPE.INFO,"LOGLY LOGS");
                }
             }
        }).start();
     }

    @RequestMapping(method = RequestMethod.GET, value = "/blastoff", produces = "application/json")
    public ResponseEntity<String> blastoff(){
        blast.set(false);
        return new ResponseEntity<>("Stopped", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/healthcheck", produces = "application/json")
    public ResponseEntity<String> health(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/healthcheck-randomfail", produces = "application/json")
    public ResponseEntity<String> healthfail(){

        int num = random.nextInt(5);

        if(num == 3){
            return new ResponseEntity<>("400", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
    }


}
