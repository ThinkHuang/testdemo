package log4j;

import java.net.URL;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jExample
{
    private static Logger log = Logger.getLogger(Log4jExample.class.getName());
    
    public static void main(String[] args)
    {
        URL url = Log4jExample.class.getClassLoader().getResource(".");
        String path = url.getPath() + "cn/huang/config/Log4j.properties";
        PropertyConfigurator.configure(path);   
        log.setLevel(Level.WARN);
        
        log.debug("Debug Message!");
        log.info("Info Message!");
        log.warn("Warn Message!");
        log.error("Error Message!");
        log.fatal("Fatal Message!");
    }
}
