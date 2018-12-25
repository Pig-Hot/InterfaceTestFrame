package verify.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Override
    public void verify(String str) {
        Object o = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            o = objectMapper.readValue(str, SQLModel.class);
            sqlModel = (SQLModel) o;
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (result.equals(sqlModel.getException())) {
            result = String.valueOf(MysqlImpl.getInstance(sqlModel.getUser(), sqlModel.getPw(), sqlModel.getUrl()).query(sqlModel.getSql()).get(0).get(sqlModel.getQuery()));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
