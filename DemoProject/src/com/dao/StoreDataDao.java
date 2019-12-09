package com.dao;

import org.springframework.transaction.annotation.Transactional;

import com.model.StoreData;

public interface StoreDataDao {
	
	@Transactional
	public void addRecords(StoreData store);

}
