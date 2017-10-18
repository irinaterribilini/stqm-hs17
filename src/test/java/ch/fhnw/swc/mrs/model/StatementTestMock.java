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
        s = mock(Statement.class, withSettings().useConstructor("Muster", "Hans", rentals).defaultAnswer(CALLS_REAL_METHODS));
        assertEquals("Muster", s.getLastName());
        assertEquals("Hans", s.getFirstName());
        assertEquals(3, s.getRentals().size());
    }

    @Test
    public void testFirstName() {
        assertIllegalArgumentCtor("Muster", "Maximilian", rentals);
    }

    @Test
    public void testLastName() {
        assertIllegalArgumentCtor("Mustermann", "Hans", rentals);
    }

    @Test
    public void testRentals() {
        assertIllegalArgumentCtor("Muster", "Hans", null);
    }

    private void assertIllegalArgumentCtor(String name, String firstName, List<Rental> rentals) {
        try {
            mock(Statement.class, withSettings().useConstructor(name, firstName, rentals).defaultAnswer(CALLS_REAL_METHODS));
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, Throwables.getRootCause(e).getClass());
        }
    }

}
