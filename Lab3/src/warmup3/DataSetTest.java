/*
 *I have adhered to the Honor Code in this assignment.
 *
 * @author Moe Ariyoshi
 * Spring 2023
 */

package warmup3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DataSetTest {

    @Test
    void testSize() {
	DataSet dSize = new DataSet();
	assertEquals(0, dSize.size(), "Not equal!");
    }

    @Test
    void testMax() {
	DataSet dMax1 = new DataSet();
	dMax1.add(20);
	dMax1.add(30);
	dMax1.add(40);
	assertEquals(40, dMax1.max(), "Not max for all positive!");
	assertThrows(IllegalStateException.class, () -> dMax1.max());

	DataSet dMax2 = new DataSet();
	dMax2.add(-10);
	dMax2.add(-20);
	dMax2.add(-30);
	assertEquals(-10, dMax2.max(), "Not max for all positive!");
	assertThrows(IllegalStateException.class, () -> dMax2.max());
	
	DataSet dMax3 = new DataSet();
	dMax3.add(10);
	dMax3.add(10);
	dMax3.add(10);
	assertEquals(10, dMax3.max(), "Not max for all same numbers!!");
	assertThrows(IllegalStateException.class, () -> dMax3.max());
	
	DataSet dMax4 = new DataSet();
	assertThrows(IllegalStateException.class, () -> dMax4.max());

    }
    

    @Test
    void testMin() {
	DataSet dMin1 = new DataSet();
	dMin1.add(20);
	dMin1.add(30);
	dMin1.add(40);
	assertEquals(20, dMin1.min(), "Not min for all positive!");
	
	DataSet dMin2 = new DataSet();
	dMin2.add(-10);
	dMin2.add(-20);
	dMin2.add(-30);
	assertEquals(-30, dMin2.min(), "Not min for all negative!");
	
	DataSet dMin3 = new DataSet();
	dMin3.add(10);
	dMin3.add(10);
	dMin3.add(10);
	assertEquals(10, dMin3.min(), "Not min for same numbers!");
	
	DataSet dMin4 = new DataSet();
	dMin4.add(0);
	dMin4.add(0);
	dMin4.add(0);
	assertEquals(0, dMin4.min(), "Not min for all zeros!");
	
	DataSet dMin5 = new DataSet();
	assertThrows(IllegalStateException.class, () -> dMin5.min());
    }

    @Test
    void testMean() {
	DataSet dMean1 = new DataSet();
	dMean1.add(32);
	dMean1.add(8);
	assertEquals(20, dMean1.mean(), "Not mean!");
	
	DataSet dMean2 = new DataSet();
	dMean2.add(-32);
	dMean2.add(-8);
	assertEquals(-20, dMean2.mean(), "Not mean!");
	
	DataSet dMean3 = new DataSet();
	dMean3.add(0);
	dMean3.add(0);
	dMean3.add(21);
	assertEquals(7, dMean3.mean(), "Not mean!");
	
	DataSet dMean4 = new DataSet();
	assertThrows(IllegalStateException.class, () -> dMean4.mean());
    }

    @Test
    void testMedian() {
	DataSet dMedian = new DataSet();
	dMedian.add(8);
	dMedian.add(32);
	dMedian.add(16);
	assertEquals(16, dMedian.median(), "Not median!");
	
	DataSet dMedian2 = new DataSet();
	assertThrows(IllegalStateException.class, () -> dMedian2.median());
    }

}
