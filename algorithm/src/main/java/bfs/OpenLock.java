package bfs;

import java.util.*;

/**
 * @author Croy Qian
 * @createDate 2021/6/25
 * @Description TODO
 */
public class OpenLock {
    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        Set<String> dead = new HashSet<String>();
        Collections.addAll(dead, deadends);
        if (dead.contains("0000")) {
            return -1;
        }
        int step = 0;
        Deque<String> stack = new ArrayDeque<String>();
        stack.addLast("0000");
        Set<String> visit = new HashSet<String>();
        visit.add("0000");
        while (!stack.isEmpty()) {
            step++;
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                String curr = stack.pollFirst();
                for (String nextStatus : get(curr)) {
                    if (!visit.contains(nextStatus) && !dead.contains(nextStatus)) {
                        if (nextStatus.equals(target)) {
                            return step;
                        }
                        stack.addLast(nextStatus);
                        visit.add(nextStatus);
                    }
                }
            }
        }
        return -1;
    }

    private char numPrev(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    private char numSucc(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }

    // 枚举 status 通过一次旋转得到的数字
    private List<String> get(String status) {
        List<String> ret = new ArrayList<String>();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char num = array[i];
            array[i] = numPrev(num);
            ret.add(new String(array));
            array[i] = numSucc(num);
            ret.add(new String(array));
            array[i] = num;
        }
        return ret;
    }

}
