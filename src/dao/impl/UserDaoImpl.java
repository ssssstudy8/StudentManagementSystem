package dao.impl;

import dao.UserDao;
import model.Page;
import model.Sex;
import model.Type;
import model.User;
import util.DateUtil;
import util.DbUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
	
	@Override
	public boolean delUserById(int id) {
		boolean result = true;
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {			
			StringBuffer sql = new StringBuffer();			
			conn = DbUtil.getConnection();
			sql.append("delete from t_user where id=?  ");
			pstm = conn.prepareStatement(sql.toString());
			pstm.setInt(1, id);
			pstm.executeUpdate();						
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return result;
	}
		
	@Override
	public int getAllCount(Map map) {
		int count = 0;
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {		
			StringBuffer sql = new StringBuffer();
			conn = DbUtil.getConnection();
			sql.append("select count(*) from t_user where 1=1  ");
			if(!StringUtil.empty(map.get("realname"))) {
				sql.append("and realname like ? ");
			}			
			pstm = conn.prepareStatement(sql.toString());
			int i = 1;
			if(!StringUtil.empty(map.get("realname"))) {
				pstm.setString(i++, "%"+map.get("realname").toString()+"%");
			}
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}						
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return count;
	}
	
	@Override
	public boolean saveUser(User user) {
		boolean result = true;
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {			
			StringBuffer sql = new StringBuffer();			
			conn = DbUtil.getConnection();
			sql.append("insert into t_user(username,realname,password,sex,birthday,tel,address,type,photo) ");
			sql.append(" values(?,?,?,?,?,?,?,?,?) ");
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, user.getUsername());
			pstm.setString(2, user.getRealname());
			pstm.setString(3, user.getPassword());
			pstm.setString(4, user.getSex());
			pstm.setDate(5,  DateUtil.toSqlDate(user.getBirthday()));
			pstm.setString(6, user.getTel());
			pstm.setString(7, user.getAddress());
			pstm.setString(8, user.getType());
			pstm.setString(9, user.getPhoto());
			pstm.executeUpdate();								
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return result;
	}
	
	@Override
	public List<Type> getAllType() {
		List<Type> types = new ArrayList<Type>();
		Type type = null;
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {			
			StringBuffer sql = new StringBuffer();			
			conn = DbUtil.getConnection();
			sql.append("select * from t_type  ");
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while(rs.next()) {
				type = new Type();
				type.setId(rs.getInt("id"));
				type.setName(rs.getString("name"));
				types.add(type);
			}						
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return types;
	}
	
	@Override
	public List<Sex> getAllSex() {
		List<Sex> sexs = new ArrayList<Sex>();
		Sex sex = null;
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {			
			StringBuffer sql = new StringBuffer();			
			conn = DbUtil.getConnection();
			sql.append("select * from t_sex  ");
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while(rs.next()) {
				sex = new Sex();
				sex.setId(rs.getInt("id"));
				sex.setName(rs.getString("name"));
				sexs.add(sex);
			}						
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return sexs;
	}
	
	@Override
	public List<User> getAll(Page page, Map map) {
		List<User> users = new ArrayList<User>();
		User user = null;
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			
			conn = DbUtil.getConnection();
			sql.append("select * from t_user where 1=1   ");
			if(!StringUtil.empty(map.get("realname"))) {
				sql.append("and realname like ? ");
			}
									
			sql.append(" limit ");
			sql.append( (page.getCurrentPage()-1)*page.getPageSize()   );
			sql.append(",");
			sql.append(page.getPageSize());
			pstm = conn.prepareStatement(sql.toString());
			
			int i = 1;
			if(!StringUtil.empty(map.get("realname"))) {
				pstm.setString(i++, "%"+map.get("realname").toString()+"%");
			}
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setRealname(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setSex(rs.getString(5));
				user.setBirthday(rs.getDate(6));
				user.setTel(rs.getString(7));
				user.setAddress(rs.getString(8));
				user.setType(rs.getString(9));
				user.setIf_valid(rs.getString(10));	
				user.setPhoto(rs.getString(11));
				users.add(user);
			}						
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return users;
	}

	@Override
	public User login(User u) {
		User user = null;
		Connection conn  = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			
			conn = DbUtil.getConnection();
			sql.append("select * from t_user where username=? and password=? ");
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, u.getUsername());
			pstm.setString(2, u.getPassword());
			rs = pstm.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setRealname(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setSex(rs.getString(5));
				user.setBirthday(rs.getDate(6));
				user.setTel(rs.getString(7));
				user.setAddress(rs.getString(8));
				user.setType(rs.getString(9));
				user.setIf_valid(rs.getString(10));							
			}						
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return user;
	}

}
