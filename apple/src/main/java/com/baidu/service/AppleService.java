package com.baidu.service;

import java.util.List;

import com.baidu.entity.LogEntity;
import com.baidu.entity.Time;

public interface AppleService {

	void saveLog(LogEntity log);

	List<Time> findMessage();

	
	
}
