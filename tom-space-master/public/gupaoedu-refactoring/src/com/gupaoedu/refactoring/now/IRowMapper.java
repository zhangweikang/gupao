package com.gupaoedu.refactoring.now;

import java.sql.ResultSet;

public interface IRowMapper<T> {
	//��������
	T mapping(ResultSet rs) throws Exception;
}
