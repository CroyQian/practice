package sort;

/**
 * @author Croy Qian
 * @createDate 2021/9/6
 * @Description 堆排序
 * java中的PriorityQueue就是堆
 */
public class HeapSort {
    public static int[] sortArray(int[] nums) {
        heapSort(nums);
        return nums;
    }

    private static void heapSort(int[] nums) {
        int len = nums.length - 1;
        buildMaxHeap(nums, len);
        //遍历当前节点，不断调整堆
        for (int i = len; i >= 1; i--) {
            swap(nums, i, 0);
            len -= 1;
            maxHeapify(nums, 0, len);
        }
    }

    private static void maxHeapify(int[] nums, int i, int len) {
        //如果当前i有子节点
//        for (; (i << 1) + 1 <= len; ) {
        while((i << 1) + 1 <= len) {
            //左孩子
            int lson = (i << 1) + 1;
            //右孩子
            int rson = (i << 1) + 2;
            //记录比较大的索引
            int largerIndex;
            if (lson <= len && nums[lson] > nums[i]) {
                largerIndex = lson;
            } else {
                largerIndex = i;
            }
            //如果有右孩子，且右孩子比当前节点大
            if (rson <= len && nums[rson] > nums[largerIndex]) {
                largerIndex = rson;
            }
            if (largerIndex != i) {
                swap(nums, i, largerIndex);
                i = largerIndex;
            } else {
                break;
            }
        }
    }

    private static void buildMaxHeap(int[] nums, int len) {
        for (int i = len / 2; i >= 0; i--) {
            maxHeapify(nums, i, len);
        }
    }

    /**
     * 将nums中索引i和索引j的数值交换
     *
     * @param nums
     * @param i
     * @param j
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] origin = {1,5,8,3,5,7,9,2,0};
        int[] array = sortArray(origin);
        for (int num : array) {
            System.out.println(num);
        }
    }
}
