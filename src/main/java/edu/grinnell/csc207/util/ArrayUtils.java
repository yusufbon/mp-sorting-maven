package edu.grinnell.csc207.util;

import java.util.Random;

/**
 * Utilities for working with arrays.
 *
 * @author Samuel A. Rebelsky
 */
public class ArrayUtils {
  // +---------------+-----------------------------------------------
  // | Static Fields |
  // +---------------+

  /**
   * A handy, dandy random-number generator.
   */
  static Random rand = new Random();

  // +----------------+----------------------------------------------
  // | Static Methods |
  // +----------------+

  /**
   * Swap two elements in an array.
   *
   * @param <T>
   *   The type of elements stored in the array.
   * @param values
   *   The array.
   * @param p
   *   The position of one element.
   * @param q
   *   The position of the other element.
   * @pre 0 *lt;= p,q &lt; values.length
   */
  public static <T> void swap(T[] values, int p, int q) {
    if (p == q) {
      return;
    } // if
    T tmp = values[p];
    values[p] = values[q];
    values[q] = tmp;
  } // swap(T[], int, int)

  /**
   * Randomly permute elements in an array.
   *
   * @param <T>
   *   The type of elements stored in the array.
   * @param values
   *   The array.
   */
  public static <T> void permute(T[] values) {
    for (int i = 0; i < values.length; i++) {
      swap(values, i, rand.nextInt(values.length));
    } // for
  } // permute(T[])

} // ArrayUtils
