package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
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
    quicksort(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * Recursively sorts subarrays using Quicksort.
   *
   * @param values the array to be sorted.
   * @param low    the starting index.
   * @param high   the ending index.
   */
  private void quicksort(T[] values, int low, int high) {
    if (low < high) {
      int[] pivots = partition(values, low, high);
      quicksort(values, low, pivots[0] - 1);
      quicksort(values, pivots[1] + 1, high);
    } //if
  } //quicksort(T[] values, int low, int high)

  /**
   * Partitions the array into three sections.
   *
   * @param values the array to partition.
   * @param low    the starting index.
   * @param high   the ending index.
   * @return the indices marking partition boundaries.
   */
  private int[] partition(T[] values, int low, int high) {
    T pivot = values[high];
    int less = low;
    int equal = low;
    int greater = high;

    while (equal <= greater) {
      if (order.compare(values[equal], pivot) < 0) {
        swap(values, less++, equal++);
      } else if (order.compare(values[equal], pivot) > 0) {
        swap(values, equal, greater--);
      } else {
        equal++;
      } //if-else
    } //while
    return new int[]{less, greater};
  } //partition(T[] values, int low, int high)

  /**
   * Swaps two elements in the array.
   *
   * @param values the array containing elements to be swapped.
   * @param i      the index of the first element.
   * @param j      the index of the second element.
   */
  private void swap(T[] values, int i, int j) {
    T temp = values[i];
    values[i] = values[j];
    values[j] = temp;
  } //swap(T[] values, int i, int j)
} // class Quicksorter
