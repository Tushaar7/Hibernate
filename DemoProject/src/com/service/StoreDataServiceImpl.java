package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.StoreDataDao;
import com.model.StoreData;

public class StoreDataServiceImpl implements StoreDataService {

	@Autowired
	StoreDataDao storedatadao;
		
	public void addRecords(StoreData store) {
		storedatadao.addRecords(store);
	}
}
