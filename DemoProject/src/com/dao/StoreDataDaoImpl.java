package com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.StoreData;

public class StoreDataDaoImpl implements StoreDataDao {

	@Autowired
	SessionFactory hibernateSessionFactory;
	
	@Transactional
	public void addRecords(StoreData store) {
		hibernateSessionFactory.getCurrentSession().save(store);
	}

}
