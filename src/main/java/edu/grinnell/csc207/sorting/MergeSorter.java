package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 * @author Bonsen Yusuf
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Merges two sorted arrays into a single sorted array.
   *
   * @param result the array to store the merged result.
   * @param left   the left sorted subarray.
   * @param right  the right sorted subarray.
   */
  private void merge(T[] result, T[] left, T[] right) {
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < left.length && j < right.length) {
      if (order.compare(left[i], right[j]) <= 0) {
        result[k++] = left[i++];
      } else {
        result[k++] = right[j++];
      } //else
    } //while
    while (i < left.length) {
      result[k++] = left[i++];
    } //while
    while (j < right.length) {
      result[k++] = right[j++];
    } //while
  } //merge(T[] result, T[] left, T[] right)


  /**
   * Sort an array in place using merge sort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    if (values.length <= 1) return;
    T[] left = Arrays.copyOfRange(values, 0, values.length / 2);
    T[] right = Arrays.copyOfRange(values, values.length / 2, values.length);
    sort(left);
    sort(right);
    merge(values, left, right);
  } // sort(T[])
} // class MergeSorter
