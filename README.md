# LoggerApp
Simple app to initiate log messages<br><br>
Usage:<br><br>
The endpoint is `/log`<br><br>
Custom attributes that can be passed in:<br>
`type` = The type of log message to do (info, warning, error --- default is info)<br>
`count` = The number of messages to log (default is 100)<br>
`message` = The message to log appended by the iteration number (default is "logger_app_message iteration number: ")<br><br>
Example endpoints:<br>
 ```java 
/log<br>
/log?count=10000000
/log?count=1400&message=hello+warning+world&type=warning
/log?count=1000&type=error
```  
    
