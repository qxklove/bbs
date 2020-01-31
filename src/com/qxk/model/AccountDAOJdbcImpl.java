package com.qxk.model;

import java.sql.*;
import javax.sql.DataSource;

public class AccountDAOJdbcImpl implements AccountDAO {
	private DataSource dataSource;
	
	public AccountDAOJdbcImpl(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	//判断用户是否存在
	public boolean isUserExisted(Account account) {
		Connection conn=null;
		PreparedStatement stmt=null;
		SQLException ex=null;
		boolean existed=false;
		try {
			conn=dataSource.getConnection();
			stmt=conn.prepareStatement("select count(1) from account where name=?");
			stmt.setString(1, account.getName());
			ResultSet rs=stmt.executeQuery();
			if (rs.next()) {
				existed=(rs.getInt(1)==1);
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
		
		return existed;
	}
	
	@Override
	//添加新用户
	public void addAccount(Account account) {
		Connection conn=null;
		PreparedStatement stmt=null;
		SQLException ex=null;
		try {
			conn=dataSource.getConnection();
			stmt=conn.prepareStatement(
					"insert into account(name,password,email)VALUES(?,?,?)");
			stmt.setString(1, account.getName());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.getEmail());
			stmt.executeUpdate();
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
	}
	
	@Override
	//获取用户
	public Account getAccount(Account account) {
		Connection conn=null;
		PreparedStatement stmt=null;
		SQLException ex=null;
		Account acct=null;
		try {
			conn=dataSource.getConnection();
			stmt=conn.prepareStatement(
					"select password,email from account where name=?" );
			stmt.setString(1, account.getName());
			ResultSet rs=stmt.executeQuery();
			if (rs.next()) {
				acct=new Account(account.getName(),rs.getString(1),rs.getString(2));
			}
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
		
		return acct;
	}
}
