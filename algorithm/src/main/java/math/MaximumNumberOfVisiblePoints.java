package math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Croy Qian
 * @createDate 2021/12/16
 * @Description 可见点的最大数目
 * 给你一个点数组 points 和一个表示角度的整数 angle ，你的位置是 location ，其中 location = [posx, posy] 且 points[i] = [xi, yi] 都表示 X-Y 平面上的整数坐标。
 * 最开始，你面向东方进行观测。你 不能 进行移动改变位置，但可以通过 自转 调整观测角度。换句话说，posx 和 posy 不能改变。你的视野范围的角度用 angle 表示， 这决定了你观测任意方向时可以多宽。设 d 为你逆时针自转旋转的度数，那么你的视野就是角度范围 [d - angle/2, d + angle/2] 所指示的那片区域。
 * @see <a href="https://leetcode-cn.com/problems/maximum-number-of-visible-points/"></a>
 * @tips
 * 1.求极角的方式有两种：
 * 使用 atan(dx/dy)：值域范围为 [-90°,90°]，需要对 dx 与 dy 进行象限讨论，从而将值域范围转化为我们希望的 [0°,360°]，同时需要注意 dx = 0dx=0 的边界情况；
 * 使用 atan2(dy, dx)atan(dy,dx)：值域范围为 [-180°,180°]，与我们期望的 [0°,360°] 相差一个固定的值，可进行统一转换，也可以直接使用。
 */
public class MaximumNumberOfVisiblePoints {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int sameCnt = 0;
        List<Double> polarDegrees = new ArrayList<>();
        int locationX = location.get(0);
        int locationY = location.get(1);
        for (List<Integer> point : points) {
            int x = point.get(0);
            int y = point.get(1);
            //统计和location相同的点
            if (x == locationX && y == locationY) {
                sameCnt++;
                continue;
            }
            //Math.atan2(1,1) -> 0.785398163(pi/4), Math.atan2(-1,-1) -> -2.356194(-pi * 3/4)
            Double degree = Math.atan2(y-locationY, x -locationX);
            polarDegrees.add(degree);
        }
        Collections.sort(polarDegrees);
        int m = polarDegrees.size();
        for (int i = 0; i < m; i++) {
            polarDegrees.add(polarDegrees.get(i) + 2 * Math.PI);
        }

        int maxCnt = 0;
        int right = 0;
        double toDegree = angle * Math.PI / 180;
        for (int i = 0; i < m; i++) {
            Double curr = polarDegrees.get(i) + toDegree;
            while (right < polarDegrees.size() && polarDegrees.get(right) <= curr) {
                right++;
            }
            maxCnt = Math.max(maxCnt, right - i);
        }
        return maxCnt + sameCnt;
    }

}
