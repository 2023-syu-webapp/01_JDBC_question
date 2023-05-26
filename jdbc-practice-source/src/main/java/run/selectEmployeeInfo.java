package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.closes;
import static common.JDBCTemplate.getConnection;

public class selectEmployeeInfo {

    // 사원 번호를 입력받아 해당 사원을 조회하고 DTO객체에 담아서 출력
    // 출력 구문은 DTO 객체의 toString() 내용과
    // "[이름]([부서명]) [직급명]님 환영합니다." 로 출력.
    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Properties prop = new Properties();

        EmployeeDTO emp = null;
        List<EmployeeDTO> eList = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("조회 할 사번을 입력 해 주세요 : ");
        String answer = sc.nextLine();
        System.out.println(answer);

        try {
            prop.loadFromXML(new FileInputStream("C:\\java22\\01_JDBC_question\\jdbc-practice-source\\src\\main\\java\\mapper\\employee-query.xml"));
            String query = prop.getProperty("select");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, answer);

            rset = pstmt.executeQuery();

            eList = new ArrayList<>();

            while(rset.next()){
                emp = new EmployeeDTO();
                emp.setEmpId(rset.getString("EMP_ID"));
                emp.setEmpName(rset.getString("EMP_NAME"));
                emp.setEmpNo(rset.getString("EMP_NO"));
                emp.setEmail(rset.getString("EMAIL"));
                emp.setPhone(rset.getString("PHONE"));
                emp.setDepCode(rset.getString("DEPT_CODE"));
                emp.setJobCode(rset.getString("JOB_CODE"));
                emp.setSallLevel(rset.getString("SAL_LEVEL"));
                emp.setSalary(rset.getInt("SALARY"));
                emp.setBonus(rset.getDouble("BONUS"));
                emp.setManagerId(rset.getString("MANAGER_ID"));
                emp.setHireDate(rset.getDate("HIRE_DATE"));
                emp.setEntDate(rset.getDate("ENT_DATE"));
                emp.setEntYn(rset.getString("ENT_YN"));

                eList.add(emp);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closes(con);
            closes(pstmt);
            closes(rset);
        }
        for(EmployeeDTO dto : eList){
            System.out.println("dto = " + dto.toString());
        }


    }
}

