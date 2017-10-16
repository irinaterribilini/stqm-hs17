package ch.fhnw.swc.mrs.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class StatementTest {
    
    private Statement s;
    private List<Rental> rentals;

    static class TestStatement extends Statement{

        /**
         * Creates a statement object for a person (with the given name parameter) and a list of rentals.
         *
         * @param name      the family name.
         * @param firstName the first name.
         * @param rentals   a list of rentals to be billed.
         */
        public TestStatement(String name, String firstName, List<Rental> rentals) {
            super(name, firstName, rentals);
        }

        @Override
        public String print() {
            return null;
        }
    }

    @Before
    public void setup() {
        Rental r1 = mock(Rental.class);
        Rental r2 = mock(Rental.class);
        Rental r3 = mock(Rental.class);

        rentals = new ArrayList<>(3);
        rentals.add(r1);
        rentals.add(r2);
        rentals.add(r3);
    }

    @Test
    public void testStatement() {
        s = new TestStatement("Muster", "Hans", rentals);
        assertEquals("Muster", s.getLastName());
        assertEquals("Hans", s.getFirstName());
        assertEquals(3, s.getRentals().size());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFirstName() {
        new TestStatement("Muster", "Maximilian", rentals);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLastName() {
       new TestStatement("Mustermann", "Hans", rentals);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testRentals() {
       new TestStatement("Muster", "Hans", null);
    }

}
