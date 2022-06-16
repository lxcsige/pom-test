package SQLParserTest;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.util.JdbcConstants;

import java.util.List;

/**
 * @author xucheng.liu
 * @since 2022/2/25
 */
public class DruidTest {

    public static void main(String[] args) {
        String testSql = "select  fdate, accountId, shopId,  \n" +
                "sum(imp) as imp, sum(click) as click \n" +
                "from table1 \n" +
                "where fdate between 2021-01-01 and 2021-01-02  \n" +
                "and accountId = 1  \n" +
                "and  shopId = 1 \n" +
                "group by fdate, accountId, shopId \n" +
                "order by imp desc\n" +
                "limit 5";

        long begin = System.currentTimeMillis();
        try {
            String dbType = JdbcConstants.MYSQL;
            List statementList = SQLUtils.parseStatements(testSql, dbType);
        } catch (Exception e) {

        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
