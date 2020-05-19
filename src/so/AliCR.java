package so;

import java.util.HashMap;
import java.util.Iterator;

/**
 * //评测题目: 如何找出一个字符串中首次出现三次且唯一出现三次的字符
 * @author theo fan
 * @version 1.0.0
 * @ClassName t.java
 * @Description TODO
 * @createTime 2020年05月19日 20:52:00
 */
public class AliCR {
    char find(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        Iterator<Character> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Character key = iterator.next();
            if (map.get(key) != 3) {
                iterator.remove();
                map.remove(key);
            }

        }
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (map.containsKey(c)) {
                Integer size = map.get(c);
                if (--size == 0) {
                    return c;
                }
                map.put(c, size);
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new AliCR().find("aabbba"));
    }
}
