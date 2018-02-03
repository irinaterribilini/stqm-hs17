package ch.fhnw.swc.mrs.data;

import ch.fhnw.swc.mrs.model.*;

import java.sql.Connection;
import java.util.List;

public class DbMRSServices implements MRSServices {
    private static final String DB_CONNECTION = "jdbc:hsqldb:hsql://localhost/xdb";
	private Database db;
	
	private MovieDAO getMovieDAO() { 
		return new SQLMovieDAO(getConnection());
	}

	private UserDAO getUserDAO() { 
		return new SQLUserDAO(getConnection());
	}

	private RentalDAO getRentalDAO() { 
		return new SQLRentalDAO(getConnection());
	}

	@Override
	public Movie createMovie(Movie movie) {
        try {
            getMovieDAO().saveOrUpdate(movie);
            return movie;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

	@Override
	public List<Movie> getAllMovies() {
		return getMovieDAO().getAll();
	}

	@Override
	public List<Movie> getAllMovies(boolean rented) {
        return getMovieDAO().getAll(rented);
	}

	@Override
	public Movie getMovieById(int id) {
	    return getMovieDAO().getById(id);
	}

	@Override
	public boolean updateMovie(Movie movie) {
	    try {
	        getMovieDAO().saveOrUpdate(movie);
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean deleteMovie(Movie movie) {
        try {
            getMovieDAO().delete(movie);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public List<User> getAllUsers() {
	    return getUserDAO().getAll();
	}

	@Override
	public User getUserById(int id) {
	    return getUserDAO().getById(id);
	}

	@Override
	public User getUserByName(String name) {
	    List<User> users = getUserDAO().getByName(name);
		return users.size() == 0 ? null : users.get(0);
	}

	@Override
	public User createUser(User user) {
	    try {
	        getUserDAO().saveOrUpdate(user);
	        return user;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public boolean updateUser(User user) {
        try {
            getUserDAO().saveOrUpdate(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean deleteUser(User user) {
        try {
            getUserDAO().delete(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public List<Rental> getAllRentals() {
		return getRentalDAO().getAll();
	}

	@Override
	public boolean createRental(User u, Movie m) {
	    Rental r = new Rental(u, m);
	    try {
	        getRentalDAO().save(r);
	        m.setRented(true);
			Bill b = new Bill(u.getFirstName(), u.getName(), u.getRentals());
			//System.out.println(b.print());
			u.getBills().add(b);
	        getMovieDAO().saveOrUpdate(m);
	    } catch (Exception e) {
	        e.printStackTrace();
	        r = null;
	    }
	    return r != null;
	}

	@Override
	public boolean returnRental(Rental r) {
        try {
            getRentalDAO().delete(r);
            Movie m = r.getMovie();
            m.setRented(false);
            getMovieDAO().saveOrUpdate(m);
        } catch (Exception e) {
            e.printStackTrace();
            r = null;
        }
        return r != null;
	}
	
	private Connection getConnection() {
	    try {
            return db.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	@Override
	public void init() {
	    try {
            db = new HsqlDatabase();
            db.initDB(DB_CONNECTION);
            // run this to clear the database
			//db.getConnection().prepareStatement("DROP SCHEMA PUBLIC CASCADE").execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
