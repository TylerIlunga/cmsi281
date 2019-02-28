package binary_search;

import java.util.Arrays;

class BinarySearch {
  private static boolean binarySearch (int[] array, int searchValue) {
    int middleIndex = array.length / 2;
    int middleValue = array[middleIndex];

    if (middleValue == searchValue) { return true; }
    if (array.length == 1) { return false; }

    if (searchValue < middleValue) {
      binarySearch(Arrays.copyOfRange(array, 0, middleIndex), searchValue);
    } else if (searchValue > middleValue) {
      binarySearch(Arrays.copyOfRange(array, middleIndex + 1, array.length), searchValue);
    }

  }

  public static void main(String[] args) {
    int[] arr = new int[11];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = i;
      System.out.println(arr[i]);
    }
    int searchValue = Integer.parseInt(args[0]);
    binarySearch(arr, searchValue);
  }
}
