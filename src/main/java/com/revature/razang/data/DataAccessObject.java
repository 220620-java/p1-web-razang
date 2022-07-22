package com.revature.razang.data;

import java.sql.SQLException;
import java.util.List;

<<<<<<< HEAD
import com.revature.razang.models.User;
=======
import com.revature.razang.exceptions.RecordNotFound;
>>>>>>> cccd2def13a57b8e25430fff3763858b1e7b7694

// data access object or "DAO"
// this interface lays out the behaviors
// that a DAO should have (CRUD methods - 
// create, read, update, delete) to
// properly interact with the data source
public interface DataAccessObject<T> {
	/**
	 * Saves the specified object as a new object in 
	 * the data source and returns the object. The returned 
	 * object may be slightly different, e.g. having an ID assigned by 
	 * the data source.
	 * 
	 * @param t the object to be added to the data source
	 * @return the object that was added or null if the object was unable to be added
	 * @throws SQLException if the unique constraint for username was violated
	 */
	public T create(T t) throws SQLException;
	
	/**
	 * Retrieves the object from the data source that matches 
	 * the specified identifier (i.e. ID, primary key, etc.).
	 * 
	 * @param the identifier of the object to be retrieved
	 * @return the retrieved object or null if no matching object was found
	 */
<<<<<<< HEAD
	public T findById(int i) throws SQLException;
=======
	public T findById(T t) throws SQLException;
>>>>>>> cccd2def13a57b8e25430fff3763858b1e7b7694
	
	/**
	 * Retrieves all of the objects from the data source.
	 * 
	 * @return a List of the retrieved objects
	 */
	public List<T> findAll();
	
	/**
	 * Updates the object in the data source by matching the identifier 
	 * and changing any different values. For example, if the object passed in 
	 * has an identifier of 1, the object with that identifier in the data source 
	 * will be updated to match the values of the object passed in.
	 * 
	 * @param t the updated object to be saved in the data source
	 * @return 
	 * @throws RecordNotFound
	 */
	public T update(T t) throws RecordNotFound;
	
	/**
	 * Deletes the object in the data source with the matching identifier.
	 * 
	 * @param t the object to be deleted from the data source
	 * @return 
	 * @throws RecordNotFound
	 */
<<<<<<< HEAD
	public T delete(T t);

	public User findById(User user);
=======
	public T delete(T t) throws RecordNotFound;
>>>>>>> cccd2def13a57b8e25430fff3763858b1e7b7694
}
