package listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Created by zhuran on 2018/11/13 0015
 * TestNG重试机制类
 */
public class TestngRetry implements IRetryAnalyzer {

    private final static Logger log = LoggerFactory.getLogger(TestngRetry.class);
    private static final int maxRetryCount = 2;
    private int retryCount = 1;

    public boolean retry(ITestResult result) {
        if (retryCount <= maxRetryCount) {
            String message = "Running retry for '" + result.getName()
                    + "' on class " + this.getClass().getName() + " Retrying "
                    + retryCount + " times";
            log.info(message);
            retryCount++;
            return true;
        }
        return false;
    }

}
