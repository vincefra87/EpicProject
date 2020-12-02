package it.epic.projects.MobileSubscriber.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.epic.projects.MobileSubscriber.model.MobileSubscriber;
import it.epic.projects.MobileSubscriber.queryFactory.MobileSubscriberManagerQuery;
import it.epic.projects.MobileSubscriber.utils.MobileSubscriberCriteria;

/**
 * @author Vincenzo Francavilla
 * 
 * Class to implements the method to access to the db
 * 
 * */
@Repository
public class MobileSubscriberRepository implements IMobileSubscriberRepository {
private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DataSource dataSource;

	private final MobileSubscriberManagerQuery queryFactory;
	
	public MobileSubscriberRepository() {
		queryFactory = new MobileSubscriberManagerQuery();
	}
	
	@Override
	public List<MobileSubscriber> findByCriteria(MobileSubscriberCriteria criteria) throws SQLException {
		
		List<MobileSubscriber> result = new ArrayList<>();
		
		Connection con =  dataSource.getConnection();

		try (PreparedStatement ps = con.prepareStatement(queryFactory.findByCriteria(criteria))) {
			ResultSet rst = ps.executeQuery();
			
			MobileSubscriber mobileSubscriber = null;

			while (rst.next()) {
				mobileSubscriber = new MobileSubscriber(rst);

				result.add(mobileSubscriber);
			}

		} catch (SQLException sqle) {
			
			logger.error("Error to find the mobile numbers: ", sqle);
			throw sqle;
		}
		return result;
	}

	@Override
	public MobileSubscriber create(MobileSubscriber mobileSubscriber) throws SQLException {
		Connection con = dataSource.getConnection();
		
		try (PreparedStatement ps = con.prepareStatement(queryFactory.insert(mobileSubscriber))) {
			ps.executeQuery();

		} catch (SQLException sqle) {
			logger.error("Error to insert the mobile subscriber: ", sqle);
			throw sqle;
		}
		return mobileSubscriber;
		
	}

	@Override
	public Integer getNewMobileSubscribeId() throws SQLException {
		Integer result = null;
		
		Connection con = dataSource.getConnection();

		try (PreparedStatement ps = con.prepareStatement(queryFactory.sqlSelNewMobileSubscriberID())) {
			ResultSet rst = ps.executeQuery();
			
			while (rst.next()) {
				
				result = rst.getInt("ID");
			}

		} catch (SQLException sqle) {
			
			logger.error("Error to get ID sequence: ", sqle);
			throw sqle;
		}
		return result;
	}

	@Override
	public MobileSubscriber update(MobileSubscriber mobileSubscriber) throws SQLException {
		Connection con = dataSource.getConnection();
		
		try (PreparedStatement ps = con.prepareStatement(queryFactory.update(mobileSubscriber))) {
			ps.executeQuery();

		} catch (SQLException sqle) {
			logger.error("Error to update the mobile subscriber: ", sqle);
			throw sqle;
		}
		return mobileSubscriber;
	}

	@Override
	public boolean delete(MobileSubscriber mobileSubscriber) throws SQLException {
		Connection con = dataSource.getConnection();
		
		try (PreparedStatement ps = con.prepareStatement(queryFactory.delete(mobileSubscriber))) {
			int i = ps.executeUpdate();
			return i > 0;

		} catch (SQLException sqle) {
			logger.error("Error to delete the mobile subscriber: ", sqle);
			throw sqle;
		}
	}

}
