package dictreenary;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class DictreenaryTests {

    // =================================================
    // Test Configuration
    // =================================================


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

    // Used as the basic empty Dictreenary to test;
    // the @Before method is run before every @Test
    Dictreenary dt;
    @Before
    public void init () {
        possible++;
        dt = new Dictreenary();
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

    // Initialization Tests
    // -------------------------------------------------
    @Test
    public void testDictreenary() {
        assertTrue(dt.isEmpty());
    }

    // Basic Tests
    // -------------------------------------------------
    @Test
    public void testAddTerm() {
        dt.addWord("is");
        dt.addWord("it");
        dt.addWord("as");
        dt.addWord("ass");
        dt.addWord("at");
        dt.addWord("bat");
    }

    @Test
    public void testAddHasWord() {
        dt.addWord("is");
        dt.addWord("it");
        dt.addWord("as");
        dt.addWord("ass");
        dt.addWord("at");
        dt.addWord("bat");
        assertTrue(dt.hasWord("is"));
        assertTrue(dt.hasWord("it"));
        assertTrue(dt.hasWord("as"));
        assertTrue(dt.hasWord("ass"));
        assertTrue(dt.hasWord("at"));
        assertTrue(dt.hasWord("bat"));
        assertFalse(dt.hasWord("ii"));
        assertFalse(dt.hasWord("i"));
        assertFalse(dt.hasWord("ba"));
        assertFalse(dt.hasWord("zoo"));
    }
    @Test
    public void testAddHasWord_t1() {
        assertFalse(dt.hasWord("a"));
        assertFalse(dt.hasWord("aa"));
        try {
            dt.addWord("");
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) {
                fail();
            }
        }
    }
    @Test
    public void testAddHasWord_t2() {
        dt.addWord("as");
        dt.addWord("at");
        dt.addWord("bass");
        dt.addWord("bat");
        assertTrue(dt.hasWord("as"));
        assertTrue(dt.hasWord("at"));
        assertTrue(dt.hasWord("bass"));
        assertTrue(dt.hasWord("bat"));
        assertFalse(dt.hasWord("ba"));
    }
    @Test
    public void testAddHasWord_t3() {
        dt.addWord("a");
        dt.addWord("at");
        dt.addWord("attack");
        dt.addWord("attacking");
        assertTrue(dt.hasWord("a"));
        assertTrue(dt.hasWord("at"));
        assertTrue(dt.hasWord("attack"));
        assertTrue(dt.hasWord("attacking"));
        assertFalse(dt.hasWord("attac"));
    }
    @Test
    public void testAddHasWord_t4() {
        dt.addWord("bit");
        dt.addWord("bite");
        dt.addWord("bitter");
        dt.addWord("bit");
        dt.addWord("bite");
        assertTrue(dt.hasWord("bit"));
        assertTrue(dt.hasWord("bite"));
        assertTrue(dt.hasWord("bitter"));
        assertFalse(dt.hasWord("bi"));
        assertFalse(dt.hasWord("bitt"));
    }
    @Test
    public void testAddHasWord_t5() {
        dt.addWord("at");
        dt.addWord("ab");
        dt.addWord("about");
        dt.addWord("abominable");
        dt.addWord("abomination");
        dt.addWord("about");
        assertTrue(dt.hasWord("at"));
        assertTrue(dt.hasWord("ab"));
        assertTrue(dt.hasWord("about"));
        assertTrue(dt.hasWord("abominable"));
        assertTrue(dt.hasWord("abomination"));
        assertFalse(dt.hasWord("a"));
        assertFalse(dt.hasWord("abomin"));
    }
    @Test
    public void testAddHasWord_t6() {
        dt.addWord("attic");
        dt.addWord("baffled");
        dt.addWord("catastrophe");
        dt.addWord("demolished");
        dt.addWord("eliminated");
        assertTrue(dt.hasWord("attic"));
        assertTrue(dt.hasWord("baffled"));
        assertTrue(dt.hasWord("catastrophe"));
        assertTrue(dt.hasWord("demolished"));
        assertTrue(dt.hasWord("eliminated"));
        assertFalse(dt.hasWord("a"));
        assertFalse(dt.hasWord("b"));
        assertFalse(dt.hasWord("c"));
        assertFalse(dt.hasWord("d"));
        assertFalse(dt.hasWord("e"));
    }
    @Test
    public void testAddHasWord_t7() {
        dt.addWord("catastrophe");
        dt.addWord("attic");
        dt.addWord("demolished");
        dt.addWord("baffled");
        dt.addWord("eliminated");
        assertTrue(dt.hasWord("attic"));
        assertTrue(dt.hasWord("baffled"));
        assertTrue(dt.hasWord("catastrophe"));
        assertTrue(dt.hasWord("demolished"));
        assertTrue(dt.hasWord("eliminated"));
        assertFalse(dt.hasWord("a"));
        assertFalse(dt.hasWord("b"));
        assertFalse(dt.hasWord("c"));
        assertFalse(dt.hasWord("d"));
        assertFalse(dt.hasWord("e"));
    }
    @Test
    public void testAddHasWord_t8() {
        dt.addWord("I");
        dt.addWord("integer");
        dt.addWord("integral");
        dt.addWord("bar");
        dt.addWord("barometer");
        dt.addWord("zoo");
        dt.addWord("zoologist");
        dt.addWord("zen");
        assertTrue(dt.hasWord("I"));
        assertTrue(dt.hasWord("integer"));
        assertTrue(dt.hasWord("integral"));
        assertTrue(dt.hasWord("bar"));
        assertTrue(dt.hasWord("barometer"));
        assertTrue(dt.hasWord("zoo"));
        assertTrue(dt.hasWord("zoologist"));
        assertTrue(dt.hasWord("zen"));
        assertFalse(dt.hasWord("in"));
        assertFalse(dt.hasWord("zool"));
        assertFalse(dt.hasWord("baro"));
    }
    @Test
    public void testAddHasWord_t9() {
        dt.addWord("integral");
        dt.addWord("integer");
        dt.addWord("I");
        dt.addWord("barometer");
        dt.addWord("bar");
        dt.addWord("zoologist");
        dt.addWord("zoo");
        dt.addWord("zen");
        assertTrue(dt.hasWord("I"));
        assertTrue(dt.hasWord("integer"));
        assertTrue(dt.hasWord("integral"));
        assertTrue(dt.hasWord("bar"));
        assertTrue(dt.hasWord("barometer"));
        assertTrue(dt.hasWord("zoo"));
        assertTrue(dt.hasWord("zoologist"));
        assertTrue(dt.hasWord("zen"));
        assertFalse(dt.hasWord("in"));
        assertFalse(dt.hasWord("zool"));
        assertFalse(dt.hasWord("baro"));
    }
    @Test
    public void testAddHasWord_t10() {
        dt.addWord("integral");
        dt.addWord("integer");
        dt.addWord("I");
        dt.addWord("barometer");
        dt.addWord("bar");
        dt.addWord("zoologist");
        dt.addWord("zoo");
        dt.addWord("zen");
        dt.addWord("I");
        dt.addWord("bar");
        dt.addWord("zoo");
        assertTrue(dt.hasWord("I"));
        assertTrue(dt.hasWord("integer"));
        assertTrue(dt.hasWord("integral"));
        assertTrue(dt.hasWord("bar"));
        assertTrue(dt.hasWord("barometer"));
        assertTrue(dt.hasWord("zoo"));
        assertTrue(dt.hasWord("zoologist"));
        assertTrue(dt.hasWord("zen"));
        assertFalse(dt.hasWord("in"));
        assertFalse(dt.hasWord("zool"));
        assertFalse(dt.hasWord("baro"));
    }
    @Test
    public void testAddHasWord_t11() {
        dt.addWord("carve");
        dt.addWord("canned");
        dt.addWord("add");
        dt.addWord("argue");
        dt.addWord("bar");
        dt.addWord("bad");
        dt.addWord("sour");
        dt.addWord("sorted");
        dt.addWord("tent");
        dt.addWord("tended");
        assertTrue(dt.hasWord("carve"));
        assertTrue(dt.hasWord("canned"));
        assertTrue(dt.hasWord("add"));
        assertTrue(dt.hasWord("argue"));
        assertTrue(dt.hasWord("bar"));
        assertTrue(dt.hasWord("bad"));
        assertTrue(dt.hasWord("sour"));
        assertTrue(dt.hasWord("sorted"));
        assertTrue(dt.hasWord("tent"));
        assertTrue(dt.hasWord("tended"));
        assertFalse(dt.hasWord("ar"));
        assertFalse(dt.hasWord("sou"));
        assertFalse(dt.hasWord("sor"));
    }

    @Test
    public void spellCheck() {
        dt.addWord("is");
        dt.addWord("it");
        dt.addWord("as");
        dt.addWord("at");
        dt.addWord("item");
        dt.addWord("ass");
        dt.addWord("bat");
        dt.addWord("bother");
        dt.addWord("goat");
        dt.addWord("goad");
        assertEquals("is", dt.spellCheck("is"));
        assertEquals("it", dt.spellCheck("it"));
        assertEquals("item", dt.spellCheck("itme"));
        assertEquals("as", dt.spellCheck("as"));
        assertEquals("bat", dt.spellCheck("abt"));
        assertEquals("bother", dt.spellCheck("bohter"));
        assertEquals(null, dt.spellCheck("bad"));
        assertEquals(null, dt.spellCheck("zoo"));
    }
    @Test
    public void spellCheck_t1() {
	    	dt.addWord("a");
        dt.addWord("ab");
        dt.addWord("about");
        dt.addWord("abort");
        assertEquals("a", dt.spellCheck("a"));
        assertEquals("ab", dt.spellCheck("ba"));
        assertEquals("about", dt.spellCheck("about"));
        assertEquals("abort", dt.spellCheck("abort"));
        assertEquals("abort", dt.spellCheck("abotr"));
        assertEquals(null, dt.spellCheck("b"));
        assertEquals(null, dt.spellCheck("abour"));
        assertEquals(null, dt.spellCheck("abortting"));
        assertEquals(null, dt.spellCheck("zzz"));
    }
    @Test
    public void spellCheck_t2() {
        dt.addWord("bit");
        dt.addWord("bite");
        dt.addWord("bitter");
        dt.addWord("bit");
        dt.addWord("bite");
        assertEquals("bit", dt.spellCheck("bit"));
        assertEquals("bite", dt.spellCheck("biet"));
        assertEquals("bite", dt.spellCheck("ibte"));
        assertEquals("bitter", dt.spellCheck("btiter"));
        assertEquals(null, dt.spellCheck("b"));
        assertEquals(null, dt.spellCheck("bi"));
        assertEquals(null, dt.spellCheck("tib"));
        assertEquals(null, dt.spellCheck("zzz"));
        assertEquals(null, dt.spellCheck("bitterirno"));
        assertEquals(null, dt.spellCheck("bitet"));
    }
    @Test
    public void spellCheck_t3() {
        dt.addWord("attic");
        dt.addWord("baffled");
        dt.addWord("catastrophe");
        dt.addWord("demolished");
        dt.addWord("eliminated");
        assertEquals("attic", dt.spellCheck("attic"));
        assertEquals("attic", dt.spellCheck("attci"));
        assertEquals("baffled", dt.spellCheck("bfafled"));
        assertEquals("catastrophe", dt.spellCheck("catastrohpe"));
        assertEquals("eliminated", dt.spellCheck("leiminated"));
        assertEquals(null, dt.spellCheck("attics"));
        assertEquals(null, dt.spellCheck("baffledd"));
        assertEquals(null, dt.spellCheck("demolishedd"));
    }
    @Test
    public void spellCheck_t4() {
        dt.addWord("catastrophe");
        dt.addWord("attic");
        dt.addWord("demolished");
        dt.addWord("baffled");
        dt.addWord("eliminated");
        assertEquals("attic", dt.spellCheck("attic"));
        assertEquals("attic", dt.spellCheck("attci"));
        assertEquals("baffled", dt.spellCheck("bfafled"));
        assertEquals("catastrophe", dt.spellCheck("catastrohpe"));
        assertEquals("eliminated", dt.spellCheck("leiminated"));
        assertEquals(null, dt.spellCheck("attics"));
        assertEquals(null, dt.spellCheck("baffledd"));
        assertEquals(null, dt.spellCheck("demolishedd"));
    }
    @Test
    public void spellCheck_t5() {
        dt.addWord("barnhouse");
        dt.addWord("barn");
        dt.addWord("bar");
        dt.addWord("ba");
        assertEquals("ba", dt.spellCheck("ba"));
        assertEquals("barnhouse", dt.spellCheck("barnhoues"));
        assertEquals("barn", dt.spellCheck("bran"));
        assertEquals(null, dt.spellCheck("barr"));
        assertEquals(null, dt.spellCheck("narb"));
    }
    @Test
    public void spellCheck_t6() {
        try {
            dt.spellCheck("");
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) {
                fail();
            }
        }
    }
    @Test
    public void spellCheck_t7() {
        assertEquals(null, dt.spellCheck("a"));
        assertEquals(null, dt.spellCheck("aa"));
    }
    @Test
    public void spellCheck_t8() {
        dt.addWord("carve");
        dt.addWord("canned");
        dt.addWord("add");
        dt.addWord("argue");
        dt.addWord("bar");
        dt.addWord("bra");
        dt.addWord("bad");
        dt.addWord("sour");
        dt.addWord("sorted");
        dt.addWord("item");
        dt.addWord("tent");
        dt.addWord("time");
        dt.addWord("tended");
        assertEquals("carve", dt.spellCheck("carev"));
        assertEquals("canned", dt.spellCheck("canned"));
        assertEquals("add", dt.spellCheck("dad"));
        assertEquals("time", dt.spellCheck("itme"));
        assertEquals("bra", dt.spellCheck("bra"));
        assertEquals("bra", dt.spellCheck("rba"));
        assertEquals("bar", dt.spellCheck("bar"));
    }

    @Test
    public void getSortedTerms() {
        dt.addWord("is");
        dt.addWord("it");
        dt.addWord("as");
        dt.addWord("itenerary");
        dt.addWord("ass");
        dt.addWord("at");
        dt.addWord("zoo");
        dt.addWord("bat");
        dt.addWord("bother");
        ArrayList<String> solution = new ArrayList<String>(Arrays.asList(
            "as", "ass", "at", "bat", "bother", "is", "it", "itenerary", "zoo"
        ));
        assertEquals(solution, dt.getSortedWords());
    }
    @Test
    public void getSortedTerms_t1() {
        ArrayList<String> solution = new ArrayList<String>();
        assertEquals(solution, dt.getSortedWords());
    }
    @Test
    public void getSortedTerms_t2() {
        dt.addWord("a");
        ArrayList<String> solution = new ArrayList<String>(Arrays.asList("a"));
        assertEquals(solution, dt.getSortedWords());
    }
    @Test
    public void getSortedTerms_t3() {
        dt.addWord("a");
        dt.addWord("b");
        dt.addWord("e");
        dt.addWord("d");
        dt.addWord("c");
        ArrayList<String> solution = new ArrayList<String>(Arrays.asList(
                "a", "b", "c", "d", "e"
        ));
        assertEquals(solution, dt.getSortedWords());
    }
    @Test
    public void getSortedTerms_t4() {
        dt.addWord("catastrophe");
        dt.addWord("attic");
        dt.addWord("demolished");
        dt.addWord("baffled");
        dt.addWord("eliminated");
        ArrayList<String> solution = new ArrayList<String>(Arrays.asList(
                "attic", "baffled", "catastrophe", "demolished", "eliminated"
        ));
        assertEquals(solution, dt.getSortedWords());
    }
    @Test
    public void getSortedTerms_t5() {
        dt.addWord("bit");
        dt.addWord("bite");
        dt.addWord("biter");
        dt.addWord("bitter");
        dt.addWord("bit");
        dt.addWord("bite");
        ArrayList<String> solution = new ArrayList<String>(Arrays.asList(
                "bit", "bite", "biter", "bitter"
        ));
        assertEquals(solution, dt.getSortedWords());
    }
    @Test
    public void getSortedTerms_t6() {
        dt.addWord("i");
        dt.addWord("integer");
        dt.addWord("integral");
        dt.addWord("bar");
        dt.addWord("barometer");
        dt.addWord("zoo");
        dt.addWord("zoologist");
        dt.addWord("zen");
        ArrayList<String> solution = new ArrayList<String>(Arrays.asList(
                "bar", "barometer", "i", "integer", "integral", "zen", "zoo", "zoologist"
        ));
        assertEquals(solution, dt.getSortedWords());
    }
    @Test
    public void getSortedTerms_t7() {
        dt.addWord("integer");
        dt.addWord("integral");
        dt.addWord("barometer");
        dt.addWord("zoologist");
        dt.addWord("zen");
        dt.addWord("i");
        dt.addWord("bar");
        dt.addWord("zoo");
        ArrayList<String> solution = new ArrayList<String>(Arrays.asList(
                "bar", "barometer", "i", "integer", "integral", "zen", "zoo", "zoologist"
        ));
        assertEquals(solution, dt.getSortedWords());
    }
    @Test
    public void getSortedTerms_t8() {
        dt.addWord("carve");
        dt.addWord("canned");
        dt.addWord("add");
        dt.addWord("argue");
        dt.addWord("bar");
        dt.addWord("bad");
        dt.addWord("sour");
        dt.addWord("sorted");
        dt.addWord("tent");
        dt.addWord("tended");
        ArrayList<String> solution = new ArrayList<String>(Arrays.asList(
                "add", "argue", "bad", "bar", "canned", "carve", "sorted", "sour", "tended", "tent"
        ));
        assertEquals(solution, dt.getSortedWords());
    }

}
