package mvel;

import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;
import org.mvel2.templates.TemplateRuntime;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xucheng.liu
 * @since 2022/2/24
 */
public class Main {

    public static void main(String[] args) {
//        String expression = "select @if{groupParam != null} @{groupParam}, @end{}";

//        System.out.println(MVEL.eval(expression, params));

        String template = "select @if{groupParam != null} @{groupParam}, @end{} \n" +
                "sum(imp) as imp, sum(click) as click  from table1 \n" +
                "where fdate between '@{beginDate}' and '@{endDate}' \n" +
                "@if{accountId != null} and accountId = @{accountId} @end{} \n" +
                "@if{shopIds != null} and shopId in (@{shopIds}) @end{} \n" +
                "@if{groupParam != null} group by @{groupParam} @end{}\n" +
                "@if{orderParam != null} order by @{orderParam} @end{}\n" +
                "@if{limitParam != null} limit @{limitParam} @end{}";
        long begin1 = System.currentTimeMillis();
        CompiledTemplate compiledTemplate = TemplateCompiler.compileTemplate(template);
        long end1 = System.currentTimeMillis();
        System.out.println(end1-begin1);

        Map<String, Object> params = new HashMap<>();
        params.put("groupParam", "fdate,accountId,shopId");
        params.put("beginDate", "2021-01-01");
        params.put("endDate", "2021-01-02");
        params.put("accountId", 1);
        params.put("shopIds", "1, 2");
        params.put("orderParam", "accountId");
        params.put("limitParam", "50");

        for (int i = 0; i <= 100; i++) {
            long begin = System.currentTimeMillis();
            params.put("accountId", i);
            String output = (String) TemplateRuntime.execute(compiledTemplate, params);
            long end = System.currentTimeMillis();
            System.out.println((end - begin));
        }
    }
}
