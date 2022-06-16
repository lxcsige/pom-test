package pair.test;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

/**
 * @author xucheng.liu
 * @since 2021/8/10
 */
public class Main {

    public static void main(String[] args) {
        Pair<Integer, Integer> pair1 = Pair.of(1, 1);
        Pair<Integer, Integer> pair2 = Pair.of(1, 2);
        Pair<Integer, Integer> pair3 = Pair.of(1,1);
        Map<Pair<Integer, Integer>, Boolean> map = Maps.newHashMap();
        map.put(pair1, true);
        map.put(pair2, true);
        map.put(pair3, false);
        System.out.println(map.get(pair1));
        System.out.println(map.get(pair3));
    }
}
