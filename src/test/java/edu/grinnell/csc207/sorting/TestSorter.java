package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Tests of Sorter objects. Please do not use this class directly.
 * Rather, you should subclass it and initialize stringSorter and
 * intSorter in a static @BeforeAll method.
 *
 * @author Bonsen Yusuf
 * @uathor Samuel A. Rebelsky
 */
public class TestSorter {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * The sorter we use to sort arrays of strings.
   */
  static Sorter<String> stringSorter = null;

  /**
   * The sorter we use to sort arrays of integers.
   */
  static Sorter<Integer> intSorter = null;

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Given a sorted array and a permutation of the array, sort the
   * permutation and assert that it equals the original.
   *
   * @param <T>
   *   The type of values in the array.
   * @param sorted
   *   The sorted array.
   * @param perm
   *   The permuted sorted array.
   * @param sorter
   *   The thing to use to sort.
   */
  public <T> void assertSorts(T[] sorted, T[] perm, Sorter<? super T> sorter) {
    T[] tmp = perm.clone();
    sorter.sort(perm);
    assertArrayEquals(sorted, perm,
      () -> String.format("sort(%s) yields %s rather than %s",
          Arrays.toString(tmp), 
          Arrays.toString(perm), 
          Arrays.toString(sorted)));
  } // assertSorts

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A fake test. I've forgotten why I've included this here. Probably
   * just to make sure that some test succeeds.
   */
  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  /**
   * Ensure that an array that is already in order gets sorted correctly.
   */
  @Test
  public void orderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that an array that is ordered backwards gets sorted correctly.
   */
  @Test
  public void reverseOrderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "foxtrot", "delta", "charlie", "bravo", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that a randomly permuted version of a moderate-sized
   * array sorts correctly.
   */
  @Test 
  public void permutedIntegersTest() { 
    int SIZE = 100; 
    if (null == intSorter) { 
      return; 
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedIntegers

  /**
   * Test sorting an array with duplicate elements.
   */
  @Test
  public void duplicateElementsTest() {
    if (intSorter == null) return;
    Integer[] original = { 5, 3, 3, 8, 2, 2, 5 };
    Integer[] expected = { 2, 2, 3, 3, 5, 5, 8 };
    assertSorts(expected, original, intSorter);
  } //duplicateElementsTest

  /**
   * Test sorting an empty array.
   */
  @Test
  public void emptyArrayTest() {
    if (intSorter == null) return;
    Integer[] original = {};
    Integer[] expected = {};
    assertSorts(expected, original, intSorter);
  } // emptyArrayTest

  /**
   * Test sorting a single-element array.
   */
  @Test
  public void singleElementTest() {
    if (stringSorter == null) return;
    String[] original = { "echo" };
    String[] expected = { "echo" };
    assertSorts(expected, original, stringSorter);
  } // singleElementTest


/**
   * Test sorting using my algorith, YusufBonsen sorter.
   */
  @Test
  public void testYusufBonsenSorter() {
    Sorter<Integer> lastFirstSorter = new YusufBonsenSorter<>(Integer::compareTo);
    Integer[] original = { 10, -5, 3, 7, 2, 0 };
    Integer[] expected = { -5, 0, 2, 3, 7, 10 };
    assertSorts(expected, original, lastFirstSorter);
  } //testYusufBonsenSorter()

  /**
   * Test sorting an array with negative numbers.
   */
  @Test
  public void testArrayWithNegatives() {
    if (intSorter == null) return;
    Integer[] original = { 3, -1, -7, 4, 2 };
    Integer[] expected = { -7, -1, 2, 3, 4 };
    assertSorts(expected, original, intSorter);
  } //testArrayWithNegatives()

  /**
   * Test sorting an array with extreme integer values.
   */
  @Test
  public void testMinMaxValuesArray() {
    if (intSorter == null) return;
    Integer[] original = { Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };
    Integer[] expected = { Integer.MIN_VALUE, 0, Integer.MAX_VALUE };
    assertSorts(expected, original, intSorter);
  } //testMinMaxValuesArray()

  /**
   * Test sorting an alternating high-low value array.
   */
  @Test
  public void testAlternatingValues() {
    if (intSorter == null) return;
    Integer[] original = { 1, 100, 2, 99, 3, 98 };
    Integer[] expected = { 1, 2, 3, 98, 99, 100 };
    assertSorts(expected, original, intSorter);
  } //testAlternatingValues()
} // class TestSorter
