package verify.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.conn.impl.MysqlImpl;
import verify.IVerify;
import verify.modle.SQLModel;

import java.io.IOException;

/**
 * Created by zhuran on 2018/12/25 0025
 */
public class SQLImpl implements IVerify {
    private SQLModel sqlModel;
    private String result = "";
    private static final Logger logger = LoggerFactory.getLogger(SQLImpl.class);
    @Override
    public void verify(String str) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            sqlModel = objectMapper.readValue(str, SQLModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Exec Sql: " + sqlModel.getSql());
        while (result.equals(sqlModel.getException())) {
            result = String.valueOf(MysqlImpl.getInstance(sqlModel.getUser(), sqlModel.getPw(), sqlModel.getUrl()).query(sqlModel.getSql()).get(0).get(sqlModel.getQuery()));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("Check Sql Result: " + sqlModel.getQuery() + " = " + sqlModel.getException() + " Success");
    }
}
