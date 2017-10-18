package ch.fhnw.swc.mrs.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

import com.google.common.base.Throwables;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class StatementTestMock {
    
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

    @Test
    public void testFirstName() {
        try {
            mock(Statement.class, withSettings().useConstructor("Muster", "Maximilian", rentals).defaultAnswer(CALLS_REAL_METHODS));
        }catch (Exception e){
            //assertTrue(Throwables.getRootCause(e) instanceof IllegalArgumentException);
            assertEquals(IllegalArgumentException.class, Throwables.getRootCause(e).getClass());
        }

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
