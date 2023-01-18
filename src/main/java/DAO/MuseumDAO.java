package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Customer;
import DTO.Museum;
import DTO.Reservation;

public class MuseumDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "test", "test1234");
		return con;
	}
	
	public String selectAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Museum> list = new ArrayList<Museum>();

		try {
			conn = getConnection();
			String sql = "SELECT M_NO, M_NAME, M_TEL FROM MUSEUM";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Museum museum = new Museum();
				museum.setM_no(rs.getString(1));
				museum.setM_name(rs.getString(2));
				museum.setM_tel(rs.getString(3));

				list.add(museum);
			}
			System.out.println(list);
			request.setAttribute("list", list);

			conn.close();
			ps.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list.jsp";
	}
	
	public int insert(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		int result = 0;
		try {
	
			
			
			conn = getConnection();
			// prepareStatement는 순서에 따라 값이 등록된다.
			String c_name = request.getParameter("c_name");
			String c_birth = request.getParameter("c_birth");
			String m_no = request.getParameter("m_no");
			String r_time = request.getParameter("r_time");
			String r_confirm = request.getParameter("r_confirm");
			System.out.println(r_confirm);

			String sql = "INSERT INTO RESERVATION (r_no, c_birth, c_name, m_no, r_time, r_confirm) VALUES(RESERVATION_SEQ.NEXTVAL,?,?,?,to_date(?,'yyyy-mm-dd'),?)";
			ps = conn.prepareStatement(sql);


			ps.setString(1, c_birth);
			ps.setString(2, c_name);
			ps.setString(3, m_no);
			ps.setString(4, r_time);
			ps.setString(5, r_confirm);

			result = ps.executeUpdate(); // 0실패, 1성공
			System.out.println(result);
			conn.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
public String selectCustomer(HttpServletRequest request, HttpServletResponse response){
	 	
		ArrayList<Reservation> list = new ArrayList<Reservation>();	
		try {
		conn = getConnection();
				
			//투표검수조회 화면 쿼리
		String sql = "SELECT R_NO, C_NAME, C_BIRTH, ";
		sql += "DECODE(M_NO, '1', 'WATERFALL', '2', 'FLOWER', '3', 'BEACH', '4', 'GARDEN', ";
		sql += "'5', 'STAR', '6', 'THUNDER', '7', 'JUNGLE', '8', 'WHALE')M_NO, TO_CHAR(R_TIME, 'YYYY-MM-DD'), ";
		sql += "DECODE(R_CONFIRM, 'Y', 'On-site payment', 'N', 'Payment completed')R_CONFIRM ";
		sql += "FROM RESERVATION";
		
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
			
		while(rs.next()) {
			Reservation reservation = new Reservation();
				
			reservation.setR_no(rs.getString(1));
			reservation.setC_name(rs.getString(2));
			reservation.setC_birth(rs.getString(3));
			reservation.setM_no(rs.getString(4));
			reservation.setR_time(rs.getString(5));
			reservation.setR_confirm(rs.getString(6));
			
			list.add(reservation);
		}
		request.setAttribute("list",list);
		conn.close();
		ps.close();
		rs.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
		return "customerlist.jsp";
	}

public String modify(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	Reservation reservation = new Reservation();
     try {
        conn = getConnection();
        String r_no = request.getParameter("r_no");
        // 수정할 회원정보 가져오기
        String sql = "SELECT C_NAME, C_BIRTH, M_NO, R_TIME, R_CONFIRM, R_NO ";
        sql += "FROM RESERVATION WHERE R_NO=" + r_no;
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
        	reservation.setC_name(rs.getString(1));
        	reservation.setC_birth(rs.getString(2));
        	reservation.setM_no(rs.getString(3));
        	reservation.setR_time(rs.getString(4));
        	reservation.setR_confirm(rs.getString(5));
        	reservation.setR_no(rs.getString(6));

        }

        request.setAttribute("reservation", reservation);

        conn.close();
        ps.close();
        rs.close();

     } catch (Exception e) {
        e.printStackTrace();
     }
     return "modify.jsp";
  }

public int update(HttpServletRequest request, HttpServletResponse response) throws ServletException {
	int result = 0;
	try {
		conn = getConnection();
		// prepareStatement는 순서에 따라 값이 등록된다.
		String c_name = request.getParameter("c_name");
		String c_birth = request.getParameter("c_birth");
		String m_no = request.getParameter("m_no");
		String r_time = request.getParameter("r_time");
		String r_confirm = request.getParameter("r_confirm");
		String r_no = request.getParameter("r_no");
		System.out.println(r_confirm);

		String sql = "UPDATE RESERVATION SET C_NAME = ? , C_BIRTH = ? , M_NO = ? ,";
		sql += "R_TIME = TO_DATE(?, 'YYYY-MM-DD') ,";
		sql += "R_CONFIRM = ? ";
		sql += "WHERE R_NO = ?";
		ps = conn.prepareStatement(sql);


		ps.setString(1, c_name);
		ps.setString(2, c_birth);
		ps.setString(3, m_no);
		ps.setString(4, r_time);
		ps.setString(5, r_confirm);
		ps.setString(6, r_no);

		result = ps.executeUpdate(); // 0실패, 1성공
		System.out.println(result);
		conn.close();
		ps.close();

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;

}
public int delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int result = 0;
    try {
       conn = getConnection();
       String r_no = request.getParameter("r_no");
       String sql = "DELETE FROM RESERVATION WHERE r_no=" + r_no;
       ps = conn.prepareStatement(sql);
       result = ps.executeUpdate();

       conn.close();
       ps.close();
    } catch (Exception e) {
       e.printStackTrace();
    }
    return result;
 }

	
}


