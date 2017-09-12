package com.stu.dao;

import java.sql.SQLException;

import com.stu.dto.Admin;

public interface IAdmin {
	public boolean login(Admin admin) throws SQLException;
}
