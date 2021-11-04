package sort;

/**
 * @author Croy Qian
 * @createDate 2021/11/4
 * @Description 冒泡排序
 */
public class BubbleSort {
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }

    public void bubbleSortEx(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean isSorted = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    isSorted = true;
                }
            }
            if (!isSorted) {
                break;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 6, 4, 7, 8, 0, 2, 3 };
        BubbleSort bubbleSort = new BubbleSort();
        //        bubbleSort.bubbleSort(arr);
        bubbleSort.bubbleSortEx(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
