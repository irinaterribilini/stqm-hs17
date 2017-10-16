package ch.fhnw.swc.mrs.data;

import ch.fhnw.swc.mrs.model.Movie;
import ch.fhnw.swc.mrs.model.Rental;
import ch.fhnw.swc.mrs.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Irina on 16.10.2017.
 */
public class ITBill{

    DbMRSServices dbMRSServices;
    User u;
    Movie m;
    Rental r;

    @Before
    public void setUp() throws Exception {
        dbMRSServices = new DbMRSServices();
        u = mock(User.class);
        m = mock(Movie.class);
        r = mock(Rental.class);

        when(u.getFirstName()).thenReturn("Trudi");
        when(u.getName()).thenReturn("Muster");
        when(u.getRentals()).thenReturn(new ArrayList<Rental>((Collection<? extends Rental>) r));
        when(r.getRentalDays()).thenReturn(21l);
        when(r.getRentalFee()).thenReturn(20d);
        when(r.getMovie()).thenReturn(m);
        when(m.getTitle()).thenReturn("Titanic");
    }

    @Test
    public void printTest() throws Exception {
        dbMRSServices.createRental(u, m);

    }
}
