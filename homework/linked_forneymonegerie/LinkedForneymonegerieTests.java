package linked_forneymonegerie;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;

import org.junit.*;
import org.junit.Before;
import org.junit.Test;
// import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.rules.TestWatcher;

public class LinkedForneymonegerieTests {

  // =================================================
  // Test Configuration
  // =================================================

  // Just in case there was some weird business with copying from
  // HW1 and not realizing that there shouldn't be a START_SIZE
  static private int START_SIZE = 16;

  // Global timeout to prevent infinite loops from
  // crashing the test suite
  // @Rule
  // public Timeout globalTimeout = Timeout.seconds(2);

  // Each time you pass a test, you get a point! Yay!
  // [!] Requires JUnit 4+ to run
  @Rule
  public TestWatcher watchman = new TestWatcher() {
      @Override
      protected void succeeded(Description description) {
          passed++;
      }
  };

  // Grade record-keeping
  static int possible = 0, passed = 0;

  // Used as the basic empty LinkedForneymonegerie to test; the @Before
  // method is run before every @Test
  LinkedForneymonegerie fm;
  @Before
  public void init () {
      possible++;
      fm = new LinkedForneymonegerie();
  }

  // Used for grading, reports the total number of tests
  // passed over the total possible
  @AfterClass
  public static void gradeReport () {
      System.out.println("============================");
      System.out.println("Tests Complete");
      System.out.println(passed + " / " + possible + " passed!");
      if ((1.0 * passed / possible) >= 0.9) {
          System.out.println("[!] Nice job!"); // Automated acclaim!
      }
      System.out.println("============================");
  }


  // =================================================
  // Unit Tests
  // =================================================
  // For grading purposes, every method has ~3 tests,
  // weighted equally and totaled for the score.
  // The tests increase in difficulty such that the
  // basics are unlabeled and harder tiers are tagged
  // t1, t2, t3, ... easier -> harder


  // Initialization Tests
  // -------------------------------------------------
  @Test
  public void testInit() {
      assertTrue(fm.empty());
      assertEquals(0, fm.size());
  }

  // Basic Tests
  // -------------------------------------------------

  @Test
  public void testSize() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      assertEquals(fm.size(), 2);
      fm.collect("Dampymon");
      assertEquals(fm.size(), 3);
  }
  @Test
  public void testSize_t1() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      assertEquals(fm.size(), 3);
  }
  @Test
  public void testSize_t2() {
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Zappymon");
      assertEquals(fm.size(), 3);
  }

  @Test
  public void testTypeSize() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      assertEquals(fm.typeSize(), 1);
      fm.collect("Dampymon");
      assertEquals(fm.typeSize(), 2);
  }
  public void testTypeSize_t1() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      assertEquals(fm.typeSize(), 1);
  }
  @Test
  public void testTypeSize_t2() {
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Zappymon");
      assertEquals(fm.typeSize(), 3);
  }
  @Test
  public void testTypeSize_t3() {
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.release("Burnymon");
      assertEquals(fm.typeSize(), 1);
      fm.release("Dampymon");
      assertEquals(fm.typeSize(), 0);
  }

  // LinkedForneymonegerie Manipulation Tests
  // -------------------------------------------------
  @Test
  public void testCollect() {
      boolean collected = fm.collect("Burnymon");
      assertTrue(collected);
      fm.collect("Burnymon");
      collected = fm.collect("Burnymon");
      assertFalse(collected);
      fm.collect("Dampymon");
      assertTrue(fm.contains("Burnymon"));
      assertTrue(fm.contains("Dampymon"));
  }
  @Test
  public void testCollect_t1() {
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Burnymon");
      assertTrue(fm.contains("Burnymon"));
      assertTrue(fm.contains("Dampymon"));
      assertEquals(fm.size(), 3);
      assertEquals(fm.typeSize(), 2);
  }
  @Test
  public void testCollect_t2() {
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Burnymon");
      fm.release("Dampymon");
      assertTrue(fm.contains("Burnymon"));
      assertFalse(fm.contains("Dampymon"));
      assertEquals(fm.size(), 2);
      assertEquals(fm.typeSize(), 1);
  }
  @Test
  public void testCollect_t3() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Zappymon");
      fm.collect("Zappymon");
      fm.release("Dampymon");
      assertEquals(fm.size(), 4);
      assertEquals(fm.typeSize(), 2);
  }
  @Test
  public void testCollect_t4() {
      for (int i = 0; i < 1000; i++) {
          fm.collect("" + i);
      }
      assertEquals(fm.size(), 1000);
      assertEquals(fm.typeSize(), 1000);
  }
  @Test
  public void testCollect_t5() {
      for (int i = 0; i < 1000; i++) {
          fm.collect("SAMESIES");
      }
      assertEquals(fm.size(), 1000);
      assertEquals(fm.typeSize(), 1);
  }

  @Test
  public void testRelease() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      assertEquals(fm.size(), 2);
      assertEquals(fm.typeSize(), 1);
      boolean released = fm.release("Burnymon");
      assertEquals(fm.size(), 1);
      assertEquals(fm.typeSize(), 1);
      assertTrue(released);
  }
  @Test
  public void testRelease_t1() {
      fm.collect("Dampymon");
      boolean released = fm.release("Dampymon");
      assertEquals(fm.size(), 0);
      assertTrue(released);
      assertFalse(fm.contains("Dampymon"));
      released = fm.release("Dampymon");
      assertFalse(released);
  }
  @Test
  public void testRelease_t2() {
      fm.release("Dampymon");
      fm.collect("Dampymon");
      fm.release("uni");
      assertEquals(fm.size(), 1);
      assertTrue(fm.contains("Dampymon"));
      fm.collect("Burnymon");
      fm.collect("Zappymon");
      fm.release("Dampymon");
      assertEquals(fm.size(), 2);
      assertFalse(fm.contains("Dampymon"));
  }
  @Test
  public void testRelease_t3() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Dampymon");
      fm.collect("Dampymon");
      fm.release("Dampymon");
      assertEquals(fm.size(), 4);
      assertEquals(fm.typeSize(), 2);
      fm.collect("Zappymon");
      fm.collect("Zappymon");
      boolean released = fm.release("Burnymon");
      assertTrue(released);
      released = fm.release("Burnymon");
      assertTrue(released);
      assertEquals(fm.size(), 4);
      assertEquals(fm.typeSize(), 2);
      assertFalse(fm.contains("Burnymon"));
  }

  @Test
  public void testReleaseAll() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      assertEquals(fm.size(), 3);
      assertEquals(fm.typeSize(), 2);
      fm.releaseType("Burnymon");
      assertEquals(fm.size(), 1);
      assertEquals(fm.typeSize(), 1);
  }
  @Test
  public void testReleaseAll_t1() {
      fm.releaseType("Dampymon");
      fm.collect("Dampymon");
      fm.releaseType("Dampymon");
      assertEquals(fm.size(), 0);
      assertFalse(fm.contains("Dampymon"));
  }
  @Test
  public void testReleaseAll_t2() {
      fm.collect("Dampymon");
      fm.collect("Burnymon");
      fm.collect("Zappymon");
      fm.releaseType("Dampymon");
      fm.releaseType("Burnymon");
      assertEquals(fm.size(), 1);
      assertFalse(fm.contains("Dampymon"));
  }
  @Test
  public void testReleaseAll_t3() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Dampymon");
      fm.collect("Zappymon");
      fm.releaseType("Burnymon");
      assertEquals(fm.size(), 3);
      assertEquals(fm.typeSize(), 2);
      assertFalse(fm.contains("Burnymon"));
      fm.releaseType("Dampymon");
      assertEquals(fm.size(), 1);
      assertEquals(fm.typeSize(), 1);
      fm.releaseType("Zappymon");
      assertEquals(fm.size(), 0);
      assertEquals(fm.typeSize(), 0);
  }

  @Test
  public void testCount() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      assertEquals(fm.countType("Burnymon"), 2);
      assertEquals(fm.countType("Dampymon"), 1);
      assertEquals(fm.countType("forneymon"), 0);
  }
  @Test
  public void testCount_t1() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      assertEquals(fm.countType("Burnymon"), 3);
      fm.releaseType("Burnymon");
      assertEquals(fm.countType("Burnymon"), 0);
  }
  @Test
  public void testCount_t2() {
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      assertEquals(fm.countType("Burnymon"), 2);
      assertEquals(fm.countType("Dampymon"), 2);
      fm.releaseType("Burnymon");
      assertEquals(fm.countType("Burnymon"), 0);
      fm.release("Dampymon");
      assertEquals(fm.countType("Dampymon"), 1);
  }

  @Test
  public void testContains() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      assertTrue(fm.contains("Burnymon"));
      assertTrue(fm.contains("Dampymon"));
      assertFalse(fm.contains("forneymon"));
  }

  @Test
  public void testGetRarest() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Zappymon");
      assertEquals(fm.rarestType(), "Zappymon");
      fm.collect("Zappymon");
      assertEquals(fm.rarestType(), "Dampymon");
  }
  @Test
  public void testGetRarest_t1() {
      assertEquals(fm.rarestType(), null);
  }
  @Test
  public void testGetRarest_t2() {
      fm.collect("Dampymon");
      fm.collect("Burnymon");
      fm.collect("Zappymon");
      assertEquals("Zappymon", fm.rarestType());
  }

  // Inter-LinkedForneymonegerie Tests
  // -------------------------------------------------
  @Test
  public void testClone() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      LinkedForneymonegerie dolly = fm.clone();
      assertEquals(dolly.countType("Burnymon"), 2);
      assertEquals(dolly.countType("Dampymon"), 1);
      dolly.collect("Zappymon");
      assertFalse(fm.contains("Zappymon"));
  }
  @Test
  public void testClone_t1() {
      LinkedForneymonegerie dolly = fm.clone();
      fm.collect("Dampymon");
      assertFalse(dolly.contains("Dampymon"));
  }
  @Test
  public void testClone_t2() {
      fm.collect("Dampymon");
      LinkedForneymonegerie dolly = fm.clone();
      dolly.collect("Burnymon");
      LinkedForneymonegerie superDolly = dolly.clone();
      superDolly.collect("Zappymon");
      assertTrue(superDolly.contains("Dampymon"));
      assertTrue(superDolly.contains("Burnymon"));
      assertFalse(dolly.contains("Zappymon"));
  }
  @Test
  public void testClone_t3() {
      for (int i = 0; i < START_SIZE; i++) {
          fm.collect("FT" + i);
      }
      LinkedForneymonegerie dolly = fm.clone();
      for (int i = 0; i < START_SIZE; i++) {
          assertTrue(dolly.contains("FT" + i));
      }
  }

  @Test
  public void testTrade() {
      LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
      fm1.collect("Burnymon");
      fm1.collect("Burnymon");
      fm1.collect("Dampymon");
      LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
      fm2.collect("yo");
      fm2.collect("sup");
      fm1.trade(fm2);
      assertTrue(fm1.contains("yo"));
      assertTrue(fm1.contains("sup"));
      assertTrue(fm2.contains("Burnymon"));
      assertTrue(fm2.contains("Dampymon"));
      assertFalse(fm1.contains("Burnymon"));
  }
  @Test
  public void testTrade_t1() {
      LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
      fm.collect("Dampymon");
      fm2.trade(fm);
      assertTrue(fm.empty());
      assertFalse(fm2.empty());
  }
  @Test
  public void testTrade_t2() {
      LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
      LinkedForneymonegerie fm3 = new LinkedForneymonegerie();
      fm2.collect("Dampymon");
      fm.collect("Burnymon");
      fm2.trade(fm);
      fm3.trade(fm2);
      assertTrue(fm2.empty());
      assertTrue(fm.contains("Dampymon"));
      assertTrue(fm3.contains("Burnymon"));
      fm.collect("Zappymon");
      assertFalse(fm2.contains("Zappymon"));
  }
  @Test
  public void testTrade_t3() {
      fm.collect("Dampymon");
      fm.trade(fm);
      assertTrue(fm.contains("Dampymon"));
      assertEquals(fm.size(), 1);
  }
  @Test
  public void testTrade_t4() {
      LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
      fm.trade(fm2);
      assertTrue(fm.empty());
      assertTrue(fm2.empty());
  }

  // Static Method Tests
  // -------------------------------------------------
  @Test
  public void testDiffMon() {
      LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
      fm1.collect("Burnymon");
      fm1.collect("Burnymon");
      fm1.collect("Dampymon");
      LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
      fm2.collect("Burnymon");
      fm2.collect("Zappymon");
      LinkedForneymonegerie fm3 = LinkedForneymonegerie.diffMon(fm1, fm2);
      assertEquals(fm3.countType("Burnymon"), 1);
      assertEquals(fm3.countType("Dampymon"), 1);
      assertFalse(fm3.contains("Zappymon"));
      fm3.collect("Leafymon");
      assertFalse(fm1.contains("Leafymon"));
      assertFalse(fm2.contains("Leafymon"));
  }
  @Test
  public void testDiffMon_t1() {
      LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
      fm1.collect("Dampymon");
      fm1.collect("Burnymon");
      LinkedForneymonegerie fm2 = LinkedForneymonegerie.diffMon(fm, fm1);
      assertEquals(fm2.size(), 0);
      assertFalse(fm2.contains("Dampymon"));
  }
  @Test
  public void testDiffMon_t2() {
      fm.collect("Dampymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
      fm1.collect("Dampymon");
      fm1.collect("Dampymon");
      LinkedForneymonegerie fm2 = LinkedForneymonegerie.diffMon(fm, fm1);
      assertEquals(fm2.size(), 1);
      assertFalse(fm2.contains("Dampymon"));
  }
  @Test
  public void testDiffMon_t3() {
      LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
      for (int i = 0; i < START_SIZE; i++) {
          fm.collect("FT" + i);
          fm1.collect("FT" + i);
      }
      LinkedForneymonegerie diff = LinkedForneymonegerie.diffMon(fm, fm1);
      assertTrue(diff.empty());
  }
  @Test
  public void testDiffMon_t4() {
      LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
      for (int i = 0; i < START_SIZE; i++) {
          fm.collect("FT" + i);
          fm.collect("FT" + i);
          fm1.collect("FT" + i);
      }
      LinkedForneymonegerie diff = LinkedForneymonegerie.diffMon(fm, fm1);
      assertTrue(LinkedForneymonegerie.sameCollection(diff, fm1));
  }
  @Test
  public void testDiffMon_t5() {
      LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
      LinkedForneymonegerie diff = LinkedForneymonegerie.diffMon(fm, fm1);
      assertTrue(diff.empty());
  }

  @Test
  public void testSameCollection() {
      LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
      fm1.collect("Burnymon");
      fm1.collect("Burnymon");
      fm1.collect("Dampymon");
      LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
      fm2.collect("Dampymon");
      fm2.collect("Burnymon");
      fm2.collect("Burnymon");
      assertTrue(LinkedForneymonegerie.sameCollection(fm1, fm2));
      assertTrue(LinkedForneymonegerie.sameCollection(fm2, fm1));
      fm2.collect("Leafymon");
      assertFalse(LinkedForneymonegerie.sameCollection(fm1, fm2));
  }
  @Test
  public void testSameCollection_t1() {
      LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
      assertTrue(LinkedForneymonegerie.sameCollection(fm, fm1));
      assertTrue(LinkedForneymonegerie.sameCollection(fm1, fm1));
      fm1.collect("Dampymon");
      assertTrue(LinkedForneymonegerie.sameCollection(fm1, fm1));
  }
  @Test
  public void testSameCollection_t2() {
      fm.collect("Dampymon");
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
      fm1.collect("Burnymon");
      fm1.collect("Dampymon");
      assertFalse(LinkedForneymonegerie.sameCollection(fm, fm1));
      fm.releaseType("Burnymon");
      fm1.releaseType("Burnymon");
      assertTrue(LinkedForneymonegerie.sameCollection(fm, fm1));
  }
  @Test
  public void testSameCollection_t3() {
      assertTrue(LinkedForneymonegerie.sameCollection(fm, new LinkedForneymonegerie()));
  }


  // Iterator Tests
  // -------------------------------------------------
  @Test
  public void testIteratorBasics() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      LinkedForneymonegerie.Iterator it = fm.getIterator();

      // Test next()
      LinkedForneymonegerie dolly = fm.clone();
      while (true) {
          String gotten = it.getType();
          assertTrue(dolly.contains(gotten));
          dolly.release(gotten);
          if (it.hasNext()) {it.next();} else {break;}
      }
      assertTrue(dolly.empty());
      assertFalse(it.hasNext());

      // Test prev()
      dolly = fm.clone();
      while (true) {
          String gotten = it.getType();
          assertTrue(dolly.contains(gotten));
          dolly.release(gotten);
          if (it.hasPrev()) {it.prev();} else {break;}
      }
      assertTrue(dolly.empty());
      assertFalse(it.hasPrev());

      int countTypeOfReplaced = fm.countType(it.getType());
      it.replaceAll("replaced!");
      assertEquals(countTypeOfReplaced, fm.countType("replaced!"));
      assertTrue(it.isValid());

      fm.collect("Zappymon");
      assertFalse(it.isValid());
  }
  @Test
  public void testIteratorBasics_t1() {
      try {
          LinkedForneymonegerie.Iterator it = fm.getIterator();
          it.next(); // Shouldn't even get here
      } catch (Exception e) {
          if (!(e instanceof IllegalStateException)) {
              fail();
          }
      }
  }

  @Test
  public void testIteratorValidity() {
      fm.collect("Burnymon");
      LinkedForneymonegerie.Iterator it = fm.getIterator();
      fm.collect("Burnymon");
      assertFalse(it.isValid());
  }
  @Test
  public void testIteratorValidity_t1() {
      fm.collect("Burnymon");
      LinkedForneymonegerie.Iterator it = fm.getIterator();
      fm.release("Burnymon");
      assertFalse(it.isValid());
  }
  @Test
  public void testIteratorValidity_t2() {
      fm.collect("Burnymon");
      LinkedForneymonegerie.Iterator it1 = fm.getIterator();
      fm.collect("Dampymon");
      LinkedForneymonegerie.Iterator it2 = fm.getIterator();
      fm.release("Dampymon");
      assertFalse(it1.isValid());
      assertFalse(it2.isValid());
  }
  @Test
  public void testIteratorValidity_t3() {
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
      fm2.collect("Zappymon");
      fm2.collect("Dampymon");
      LinkedForneymonegerie.Iterator it1 = fm.getIterator();
      LinkedForneymonegerie.Iterator it2 = fm2.getIterator();
      fm.trade(fm2);
      assertFalse(it1.isValid());
      assertFalse(it2.isValid());
  }

  @Test
  public void testIteratorNext() {
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Dampymon");
      fm.collect("Zappymon");
      LinkedForneymonegerie.Iterator it = fm.getIterator();
      String[] soln = {"Burnymon", "Dampymon", "Dampymon", "Zappymon"};
      for (int i = 0; i < soln.length; i++) {
          assertEquals(soln[i], it.getType());
          if (it.hasNext()) it.next();
      }
      assertFalse(it.hasNext());
  }
  @Test
  public void testIteratorNext_t1() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Zappymon");
      LinkedForneymonegerie.Iterator it = fm.getIterator();
      LinkedForneymonegerie dolly = fm.clone();
      while (true) {
          String gotten = it.getType();
          assertTrue(dolly.contains(gotten));
          dolly.release(gotten);
          if (it.hasNext()) {it.next();} else {break;}
      }
      assertTrue(dolly.empty());
      assertFalse(it.hasNext());
  }
  @Test
  public void testIteratorNext_t2() {
      try {
          fm.collect("Burnymon");
          LinkedForneymonegerie.Iterator it = fm.getIterator();
          it.next();
          it.next();
      } catch (Exception e) {
          if (!(e instanceof NoSuchElementException)) {
              fail();
          }
      }
  }

  @Test
  public void testIteratorPrev() {
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Dampymon");
      fm.collect("Zappymon");
      LinkedForneymonegerie.Iterator it = fm.getIterator();
      String[] soln = {"Burnymon", "Dampymon", "Dampymon", "Zappymon"};
      while (it.hasNext()) {it.next();}
      for (int i = soln.length - 1; i > 0; i--) {
          assertEquals(soln[i], it.getType());
          if (it.hasPrev()) it.prev();
      }
      assertFalse(it.hasPrev());
  }
  @Test
  public void testIteratorPrev_t1() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Zappymon");
      LinkedForneymonegerie.Iterator it = fm.getIterator();
      LinkedForneymonegerie dolly = fm.clone();
      while (it.hasNext()) {it.next();}
      while (true) {
          String gotten = it.getType();
          assertTrue(dolly.contains(gotten));
          dolly.release(gotten);
          if (it.hasPrev()) {it.prev();} else {break;}
      }
      assertTrue(dolly.empty());
      assertFalse(it.hasPrev());
  }
  @Test
  public void testIteratorPrev_t2() {
      try {
          fm.collect("Burnymon");
          fm.collect("Burnymon");
          fm.collect("Burnymon");
          LinkedForneymonegerie.Iterator it = fm.getIterator();
          it.next();
          it.next();
          it.prev();
          it.prev();
          it.prev();
      } catch (Exception e) {
          if (!(e instanceof NoSuchElementException)) {
              fail();
          }
      }
  }
  @Test
  public void testIteratorPrev_t3() {
      try {
          fm.collect("Burnymon");
          LinkedForneymonegerie.Iterator it = fm.getIterator();
          fm.collect("Burnymon");
          it.prev();
      } catch (Exception e) {
          if (!(e instanceof IllegalStateException)) {
              fail();
          }
      }
  }

  // Small helper function to test replaceAll
  public static void advanceIteratorTo (String toFind, LinkedForneymonegerie.Iterator it) {
      while (it.hasNext()) {
          if (it.getType().equals(toFind)) {
              return;
          }
          it.next();
      }
  }

  @Test
  public void testIteratorReplaceAll() {
      fm.collect("Burnymon");
      LinkedForneymonegerie.Iterator it = fm.getIterator();
      it.replaceAll("Dampymon");
      assertTrue(it.isValid());
      assertEquals("Dampymon", it.getType());
      assertEquals(1, fm.countType("Dampymon"));
      assertEquals(0, fm.countType("Burnymon"));
  }
  @Test
  public void testIteratorReplaceAll_t1() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      LinkedForneymonegerie.Iterator it = fm.getIterator();
      it.replaceAll("Dampymon");
      assertTrue(it.isValid());
      assertEquals("Dampymon", it.getType());
      assertEquals(2, fm.countType("Dampymon"));
      assertEquals(0, fm.countType("Burnymon"));
  }
  @Test
  public void testIteratorReplaceAll_t2() {
      fm.collect("Burnymon");
      fm.collect("Zappymon");
      fm.collect("Zappymon");
      fm.collect("Burnymon");
      fm.collect("Leafymon");
      LinkedForneymonegerie.Iterator it = fm.getIterator();
      advanceIteratorTo("Zappymon", it);
      it.replaceAll("Dampymon");
      assertTrue(it.isValid());
      assertEquals("Dampymon", it.getType());
      assertEquals(2, fm.countType("Dampymon"));
      assertEquals(0, fm.countType("Zappymon"));
  }
  @Test
  public void testIteratorReplaceAll_t3() {
      fm.collect("Dampymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Burnymon");
      fm.collect("Zappymon");
      LinkedForneymonegerie.Iterator it = fm.getIterator();
      advanceIteratorTo("Burnymon", it);
      it.replaceAll("Burnymon");
      assertTrue(it.isValid());
      assertEquals("Burnymon", it.getType());
      assertEquals(2, fm.countType("Burnymon"));
      assertEquals(2, fm.countType("Dampymon"));
  }
  @Test
  public void testIteratorReplaceAll_t4() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Dampymon");
      fm.collect("Zappymon");
      LinkedForneymonegerie.Iterator it = fm.getIterator();
      advanceIteratorTo("Dampymon", it);
      it.replaceAll("Burnymon");
      assertTrue(it.isValid());
      assertTrue(it.hasNext());
      assertEquals("Burnymon", it.getType());
      assertEquals(4, fm.countType("Burnymon"));
      assertEquals(0, fm.countType("Dampymon"));
  }
  @Test
  public void testIteratorReplaceAll_t5() {
      fm.collect("Burnymon");
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Dampymon");
      fm.collect("Zappymon");
      LinkedForneymonegerie.Iterator it = fm.getIterator();
      advanceIteratorTo("Burnymon", it);
      it.replaceAll("Zappymon");
      assertTrue(it.isValid());
      assertEquals(0, fm.countType("Burnymon"));
      assertEquals(3, fm.countType("Zappymon"));
      it = fm.getIterator();
      LinkedForneymonegerie dolly = fm.clone();
      while (true) {
          String gotten = it.getType();
          assertTrue(dolly.contains(gotten));
          dolly.release(gotten);
          if (it.hasNext()) {it.next();} else {break;}
      }
      assertTrue(dolly.empty());
  }

  // BONUS Method Tests
  // Because there was some skeleton ambiguity over
  // implementing toString, you can earn some bonus
  // points if you did so successfully
  // -------------------------------------------------

  @Test
  public void testToString() {
      possible--; // For bonus' sake
      fm.collect("Burnymon");
      assertEquals("[ \"Burnymon\": 1 ]", fm.toString());
  }

  @Test
  public void testToString_t1() {
      possible--;
      fm.collect("Burnymon");
      fm.collect("Dampymon");
      fm.collect("Burnymon");
      fm.collect("Leafymon");
      fm.collect("Zappymon");
      fm.collect("Leafymon");
      assertEquals("[ \"Burnymon\": 2, \"Dampymon\": 1, \"Leafymon\": 2, \"Zappymon\": 1 ]", fm.toString());
  }

}
