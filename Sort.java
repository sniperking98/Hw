
import java.util.*;

public class Sort {

    public static int[] insertion_sort(int[] array) {
        int i, j, temp;

        for (i = 1; i < array.length; i++) {
            //store the current element in temp
            temp = array[i];

            //set j to i - 1
            j = i - 1;

            //all elements greater than temp are shifted 1 position right
            while (j >= 0 && array[j] > temp) {
                //shifted 1 position
                array[j + 1] = array[j];

                j--;
            }
            array[j + 1] = temp;
        }
        return array;
    }

    public static int[] merge_sort(int[] array, int p, int r) {
        //if index is right
        if (p < r) {
            // get the mid index
            int mid = (p + r) / 2;

            //sort left subarray
            merge_sort(array, p, mid);

            //sort the right subarray
            merge_sort(array, mid + 1, r);

            //merge the two subarrays
            merge(array, p, mid, r);

        }
        return array;
    }

    public static int[] merge(int[] array, int p, int q, int r) {
        //get the length of the left subarray
        int n1 = q - p + 1;

        //get the length of the right subarray
        int n2 = r - q;

        int i, j, k;

        //create two arrays left and right
        int[] left = new int[n1 + 1];
        int[] right = new int[n2 + 1];

        //fill left with the contents of left subarray of array
        for (i = 0; i < n1; i++) {
            left[i] = array[p + i];
            //set the last element to infinity
            left[n1] = Integer.MAX_VALUE;
            right[n2] = Integer.MAX_VALUE;

            i = 0;
            j = 0;

            for (k = p; k <= r; k++) {
                //if the current element of left is smaller than current element of right
                if (left[i] <= right[j]) {
                    array[k] = right[j];
                    i++;
                } else {
                    array[k] = right[j];
                    j++;
                }
            }
        }
        return array;
    }

    /* 
    * n: the size of the output array 
    * k: the maximum value in the array 
     */
    public static int[] enerate_random_array(int n, int k) {
        List<Integer> list;
        int[] array;
        Random rnd;

        rnd = new Random(System.currentTimeMillis());
        list = new ArrayList<Integer>();

        for (int i = 1; i <= n; i++) {
            list.add(new Integer(rnd.nextInt(k + 1)));
        }
        Collections.shuffle(list, rnd);

        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = list.get(i).intValue();
        }
        return array;
    }
    
    /*
    * n: the size of the output array
    */
    public static int[] generate_random_array(int n) {
        List<Integer> list;
        int[] array;

        list = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            list.add(new Integer(i));
        }

        Collections.shuffle(list, new Random(System.currentTimeMillis()));

        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = list.get(i).intValue();
        }
        return array;
    }
                            //stopeed here 
    /* 
     * Input: an integer array 
     * Output: true if the array is acsendingly sorted, otherwise return false 
     */
    public static boolean check_sorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    public static void print_array(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //TODO Auto-generated method stub
        System.out.println("Insertion sort starts ------------------");

        for (int n = 100000; n <= 1000000; n = n + 100000) {
            int[] array = Sort.generate_random_array(n);
            long t1 = System.currentTimeMillis();
            array = Sort.insertion_sort(array);
            long t2 = System.currentTimeMillis();
            long t = t2 - t1;
            boolean flag = Sort.check_sorted(array);
            System.out.println(n + "," + t + "," + flag);
        }
        System.out.println("Insertion sort ends ------------------");

        System.out.println("Merge sort starts --------------------");

        for (int n = 100000; n <= 1000000; n = n + 100000) {
            int[] array = Sort.generate_random_array(n);
            //Sort.print_array(array);
            long t1 = System.currentTimeMillis();
            array = Sort.merge_sort(array, 0, n - 1);
            long t2 = System.currentTimeMillis();
            long t = t2 - t1;
            //Sort.print_array(array);
            boolean flag = Sort.check_sorted(array);
            System.out.println(n + "," + t + "," + flag);
        }
        System.out.println("Merge sort ends --------------------");
    }
}
