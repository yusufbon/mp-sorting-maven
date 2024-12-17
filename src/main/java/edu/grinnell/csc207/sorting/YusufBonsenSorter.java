package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * A custom sorting algorithm that dynamically chooses between sorting strategies
 * based on input size and pre-sortedness.
 *
 * This algorithm uses Merge Sort for large arrays and switches to Insertion Sort
 * for smaller subarrays, improving performance on nearly sorted data.
 *
 * @author Bonsen Yusuf
 * @param <T> the type of elements to be sorted.
 */
public class YusufBonsenSorter<T> implements Sorter<T> {
  private Comparator<? super T> order;

  /**
   * Constructs a LastFirstSorter with the specified comparator.
   *
   * @param comparator the comparator defining the order of elements.
   */
  public YusufBonsenSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } //YusufBonsenSorter(Comparator<? super T> comparator)

  /**
   * Sorts the specified array using a hybrid sorting algorithm.
   *
   * @param values the array to be sorted.
   */
  @Override
  public void sort(T[] values) {
    if (values == null || values.length <= 1) {
      return;
    } //if
    hybridSort(values, 0, values.length - 1);
  } //sort(T[] values)

  /**
   * Recursively sorts subarrays using a hybrid approach.
   *
   * @param values the array to be sorted.
   * @param low    the starting index.
   * @param high   the ending index.
   */
  private void hybridSort(T[] values, int low, int high) {
    int threshold = 10;
    if (high - low <= threshold) {
      insertionSort(values, low, high);
    } else {
      int mid = low + (high - low) / 2;
      hybridSort(values, low, mid);
      hybridSort(values, mid + 1, high);
      merge(values, low, mid, high);
    } //else
  } //hybridSort(T[] values, int low, int high)

  /**
   * Sorts the array using insertion sort for smaller subarrays.
   *
   * @param values the array to be sorted.
   * @param low    the starting index.
   * @param high   the ending index.
   */
  private void insertionSort(T[] values, int low, int high) {
    for (int i = low + 1; i <= high; i++) {
      T key = values[i];
      int j = i - 1;
      while (j >= low && order.compare(values[j], key) > 0) {
        values[j + 1] = values[j];
        j--;
      } //while
      values[j + 1] = key;
    } //for
  } //insertionSort(T[] values, int low, int high)

  /**
   * Merges two sorted subarrays into a single sorted subarray.
   *
   * @param values the array to merge.
   * @param low    the starting index of the first subarray.
   * @param mid    the ending index of the first subarray.
   * @param high   the ending index of the second subarray.
   */
  private void merge(T[] values, int low, int mid, int high) {
    T[] left = java.util.Arrays.copyOfRange(values, low, mid + 1);
    T[] right = java.util.Arrays.copyOfRange(values, mid + 1, high + 1);

    int i = 0;
    int j = 0; 
    int k = low;

    while (i < left.length && j < right.length) {
      if (order.compare(left[i], right[j]) <= 0) {
        values[k++] = left[i++];
      } else {
        values[k++] = right[j++];
      } //else
    } //while

    while (i < left.length) {
      values[k++] = left[i++];
    } //while

    while (j < right.length) {
      values[k++] = right[j++];
    } //while
  } //merge(T[] values, int low, int mid, int high)
} //class YusufBonsenSoter
