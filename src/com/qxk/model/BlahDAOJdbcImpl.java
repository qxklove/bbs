package com.qxk.model;

import java.sql.*;
import java.util.*;
import javax.sql.DataSource;


public class BlahDAOJdbcImpl implements BlahDAO{
	private DataSource dataSource;
	
	public BlahDAOJdbcImpl(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	//获取留言列表
	public List<Blah> getBlahs(Blah blah){
		Connection conn=null;
		PreparedStatement stmt=null;
		SQLException ex=null;
		List<Blah> blahs=null;
		try {
			conn=dataSource.getConnection();
			stmt=conn.prepareStatement(
					"select date,txt from blah where name=?");
			stmt.setString(1, blah.getUsername());
			ResultSet rs=stmt.executeQuery();
			blahs=new ArrayList<Blah>();
			while (rs.next()) {
				blahs.add(new Blah(
						blah.getUsername(), rs.getTimestamp(1),rs.getString(2)));
				
			}
		} catch (SQLException e) {
			ex=e;
		}
		finally {
			if (stmt != null) {
                try {
                    stmt.close();
                }
                catch(SQLException e) {
                    if(ex == null) {
                        ex = e;
                    }
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                }
                catch(SQLException e) {
                    if(ex == null) {
                        ex = e;
                    }
                }
            }
            
            if(ex != null) {
                throw new RuntimeException(ex);
            }
		}
		return blahs;
	}
	
	@Override
	//添加留言
	public void addBlah(Blah blah) {
		Connection conn=null;
		PreparedStatement stmt=null;
		SQLException ex=null;
		try {
			conn=dataSource.getConnection();
			stmt=conn.prepareStatement(
					"insert into blah(name,date,txt)values(?,?,?)");
			stmt.setString(1, blah.getUsername());
			stmt.setTimestamp(2, new Timestamp(blah.getDate().getTime()));
			stmt.setString(3, blah.getTxt());
			stmt.executeUpdate();
		}catch (SQLException e) {
			ex=e;
		}
		finally {
			if (stmt != null) {
                try {
                    stmt.close();
                }
                catch(SQLException e) {
                    if(ex == null) {
                        ex = e;
                    }
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                }
                catch(SQLException e) {
                    if(ex == null) {
                        ex = e;
                    }
                }
            }
            
            if(ex != null) {
                throw new RuntimeException(ex);
            }
		}
	}
	
	@Override
	//删除留言
	public void deleteBlah(Blah blah) {
		Connection conn=null;
		PreparedStatement stmt=null;
		SQLException ex=null;
		try {
			conn=dataSource.getConnection();
			stmt=conn.prepareStatement(
					"delete from blah where date=?");
			stmt.setTimestamp(1, new Timestamp(blah.getDate().getTime()));
			stmt.executeUpdate();
		}catch (SQLException e) {
			ex=e;
		}
		finally {
			if (stmt != null) {
                try {
                    stmt.close();
                }
                catch(SQLException e) {
                    if(ex == null) {
                        ex = e;
                    }
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                }
                catch(SQLException e) {
                    if(ex == null) {
                        ex = e;
                    }
                }
            }
            
            if(ex != null) {
                throw new RuntimeException(ex);
            }
		}
	}
}
