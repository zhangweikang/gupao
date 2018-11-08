package com.gupaoedu.refactoring.old;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.gupaoedu.refactoring.model.Student;

public class StudentDao {

	public void save(Student stu) {
		String sql = "INSERT INTO t_student(name,age) VALUES(?,?)";
		Connection conn = null;
		Statement st = null;
		try {
			// 1. ����ע������
			Class.forName("com.mysql.jdbc.Driver");
			// 2. ��ȡ���ݿ�����
			conn = DriverManager.getConnection("jdbc:mysql:///jdbcdemo", "root", "root");
			// 3. ����������
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, stu.getName());
			ps.setObject(2, stu.getAge());
			// 4. ִ��SQL���
			ps.executeUpdate();
			// 5. �ͷ���Դ
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ɾ��ѧ����Ϣ
	public void delete(Long id) {
		String sql = "DELETE  FROM t_student WHERE id=?";
		Connection conn = null;
		Statement st = null;
		try {
			// 1. ����ע������
			Class.forName("com.mysql.jdbc.Driver");
			// 2. ��ȡ���ݿ�����
			conn = DriverManager.getConnection("jdbc:mysql:///jdbcdemo", "root", "root");
			// 3. ����������
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, id);
			// 4. ִ��SQL���
			ps.executeUpdate();
			// 5. �ͷ���Դ
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}

	// �޸�ѧ����Ϣ
	public void update(Student stu) {
		String sql = "UPDATE t_student SET name=?,age=? WHERE id=?";
		Connection conn = null;
		Statement st = null;
		try {
			// 1. ����ע������
			Class.forName("com.mysql.jdbc.Driver");
			// 2. ��ȡ���ݿ�����
			conn = DriverManager.getConnection("jdbc:mysql:///jdbcdemo", "root", "root");
			// 3. ����������
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, stu.getName());
			ps.setObject(2, stu.getAge());
			ps.setObject(3, stu.getId());
			// 4. ִ��SQL���
			ps.executeUpdate();
			// 5. �ͷ���Դ
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
