package sort;

/**
 * @author Croy Qian
 * @createDate 2021/4/13
 * @Description TODO
 */
public class QuickSort {
    private static void quickSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int partition = partition(a, start, end);
        quickSort(a, start, partition - 1);
        quickSort(a, partition + 1, end);
    }

    private static int partition(int[] a, int p, int r) {
        int soldier = a[p];
        int i = p;
        int j = r;
        while (i < j) {
            while(i<j && a[j] > soldier) {
                j--;
            }
            while(i < j && a[i] <= soldier) {
                i++;
            }
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        a[p] = a[j];
        a[j] = soldier;
        return j;
    }

    public static void main(String[] args) {
        int a[] = new int[]{5,1,1,2,0,0};
        partition(a, 0 ,a.length -1);
        quickSort(a, 0 ,a.length -1);
    }
}
