package SQLParserTest;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.parser.SqlParser;

/**
 * @author xucheng.liu
 * @since 2022/2/25
 */
public class CalciteTest {

    public static void main(String[] args) {
        SqlParser.Config config = SqlParser.configBuilder().setLex(Lex.MYSQL)
                .setCaseSensitive(false)
                .build();
        SqlParser sqlParser = SqlParser.create("", config);
        long begin = System.currentTimeMillis();
        try {
            sqlParser.parseStmt();
        } catch (Exception e) {
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
