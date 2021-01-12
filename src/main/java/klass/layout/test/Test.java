package klass.layout.test;

import com.google.common.collect.Maps;
import org.openjdk.jol.datamodel.CurrentDataModel;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.layouters.HotSpotLayouter;

import java.util.Map;

/**
 * @author xucheng.liu
 * @date 2020/9/22
 */
public class Test {

    public static void main(String[] args) {
        Map<Integer, Double> map = Maps.newHashMap();
        for (int i = 0; i <= Integer.MAX_VALUE; i++) {
            map.put(i, 1d);
        }
        System.out.println(ClassLayout.parseInstance(map, new HotSpotLayouter(new CurrentDataModel())).toPrintable());
        System.out.println("--------------------------");
    }
}
