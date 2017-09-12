package com.stu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stu.dao.IAdmin;
import com.stu.db.DataBase;
import com.stu.dto.Admin;

public class AdminImpl implements IAdmin {

	@Override
	public boolean login(Admin admin) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean falg=false;
		try {
			con=DataBase.getConn();
			ps=con.prepareStatement("select * from admin where name=? and pass=?");
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getPass());
			rs=ps.executeQuery();
			if(rs.next()){
				falg=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			rs.close();
			ps.close();
			con.close();
		}
		return falg;
	}

}
