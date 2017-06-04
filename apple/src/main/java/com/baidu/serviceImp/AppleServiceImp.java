package com.baidu.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.entity.LogEntity;
import com.baidu.entity.Time;
import com.baidu.mapper.AppleMapper;
import com.baidu.service.AppleService;
@EnableTransactionManagement
@Service
public class AppleServiceImp implements AppleService{
	@Autowired
	private AppleMapper appleMapper; 
	
	@Override
	@Transactional
	public void saveLog(LogEntity log) {
		// TODO Auto-generated method stub
		this.appleMapper.saveLog(log);
	}

	@Override
	public List<Time> findMessage() {
		// TODO Auto-generated method stub
		return this.appleMapper.findMessage();
	}
	

}
