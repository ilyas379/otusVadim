package testNum;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class FirstTest {
    private static final Logger logger = LogManager.getLogger(FirstTest.class);
    public static void main(String[] args) {

    }
    @Test
    public void test1(){
        logger.info("INFO1");
        logger.debug("DEBUG1");
        logger.error("ERROR1");
        logger.fatal("FATAL1");
        logger.warn("WARN1");
    }
}
