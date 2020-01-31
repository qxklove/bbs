package com.qxk.model;

import java.util.*;

public class UserService {
    
	//最新的留言列表
    private LinkedList<Blah> newest = new LinkedList<Blah>();
    
    private AccountDAO accountDAO;
    private BlahDAO blahDAO;
    
    public UserService(AccountDAO userDAO,BlahDAO blahDAO) {
    	this.accountDAO=userDAO;
    	this.blahDAO=blahDAO;
    }
    
    //判断用户是否存在
    public boolean isUserExisted(Account account) {
    	return accountDAO.isUserExisted(account);
    }
    
    //添加新用户
    public void add(Account account) {
    	accountDAO.addAccount(account);
    }
    
    //判断用户是否已经登录
    public boolean checkLogin(Account account) {
    	if (account.getName()!=null&&account.getPassword()!=null) {
			Account storedAcct=accountDAO.getAccount(account);
			return storedAcct !=null&&storedAcct.getPassword().equals(account.getPassword());
		}
    	return false;
    }
    
    //判断那条留言更新
    private class DateComparator implements Comparator<Blah> {
        @Override
        public int compare(Blah b1, Blah b2) {
            return -b1.getDate().compareTo(b2.getDate());
        }
    }
    
    private DateComparator comparator = new DateComparator();
    
    //获取留言
    public List<Blah> getBlahs(Blah blah){
    	List<Blah> blahs =blahDAO.getBlahs(blah);
    	Collections.sort(blahs,comparator);
    	return blahs;
    }
    
    //添加留言
    public void addBlah(Blah blah) {
    	blahDAO.addBlah(blah);
    	newest.addFirst(blah);
    	if(newest.size()>20) {
    		newest.removeLast();
    	}
    }
    
    //删除留言
    public void deleteBlah(Blah blah) {
    	blahDAO.deleteBlah(blah);
    	newest.remove(blah);
    }
    
    //获取最新留言
    public List<Blah> getNewest(){
    	return newest;
    }
}
