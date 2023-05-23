package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;
public class selectEmployeeInfo {

    // 사원 번호를 입력받아 해당 사원을 조회하고 DTO객체에 담아서 출력
    // 출력 구문은 DTO 객체의 toString() 내용과
    // "[이름]([부서명]) [직급명]님 환영합니다." 로 출력.

    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 사원 번호를 입력하세요: ");
        String empId = sc.nextLine();

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("selectEmp");
            System.out.println(query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                String empName = rs.getString("EMP_NAME");
                String deptTitle = rs.getString("DEPT_TITLE");
                String jobName = rs.getString("JOB_NAME");


                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setEmpName(empName);
                employeeDTO.setDeptTitle(deptTitle);
                employeeDTO.setJobName(jobName);

                System.out.println(employeeDTO.toString());
                System.out.println("[ " + employeeDTO.getJobName() + " ]" +
                        "([ " + employeeDTO.getDeptTitle() + " ]) " +
                        employeeDTO.getEmpName() + "님 환영합니다.");
            } else {
                System.out.println("해당 사원을 조회할 수 없습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
            close(rs);
        }
    }

}
