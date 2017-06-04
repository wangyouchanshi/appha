package com.baidu.mapper;

import java.util.List;

import com.baidu.entity.LogEntity;
import com.baidu.entity.Time;
public interface AppleMapper {

	void saveLog(LogEntity log);

	List<Time> findMessage();

}
