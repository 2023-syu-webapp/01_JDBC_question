package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class insertEmployeeInfo {

    // 사원 정보 전체를 입력받아 DTO객체에 담아서 insert
    // insert 성공하면 "직원 등록에 성공하였습니다." 출력
    // insert 실패하면 "직원 등록에 실패하였습니다." 출력
        public static void main(String[] args) {
            Connection con = getConnection();

            PreparedStatement pstmt = null;
            int result = 0;

            Properties prop = new Properties();
            EmployeeDTO empDTO = null;

            try {
                prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));

                String query = prop.getProperty("insertEmployee");
                System.out.println(query);

                Scanner sc = new Scanner(System.in);
                System.out.print("직원의 이름을 입력하세요 :");
                String name = sc.next();
                System.out.print("직원의 주민번호를 입력하세요 :");
                String no = sc.next();
                System.out.print("직원의 이메일을 입력하세요 :");
                String email = sc.next();
                System.out.print("직원의 전화번호을 입력하세요 :");
                String phone = sc.next();
                System.out.print("직원의 부서번호을 입력하세요 :");
                String deptCode = sc.next();
                System.out.print("직원의 직업번호을 입력하세요 :");
                String jobCode = sc.next();
                System.out.print("직원의 임금레벨을 입력하세요 :");
                String salLevel = sc.next();
                System.out.print("직원의 급여을 입력하세요 :");
                int sal = sc.nextInt();
                System.out.print("직원의 보너스을 입력하세요 :");
                Double bonus = sc.nextDouble();
                System.out.print("직원의 관리아이디을 입력하세요 :");
                String manage = sc.next();

                System.out.print("직원의 고용일을 입력하세요 :");
                String hire = sc.next();
                DateTimeFormatter Format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(hire, Format);


                System.out.print("직원의 ENT_DATE을 입력하세요 :");
                String eDate = sc.next();
                LocalDate date2 = LocalDate.parse(eDate, Format);

                System.out.print("직원의 ENT_YN을 입력하세요 :");
                String eYn = sc.next();

                empDTO = new EmployeeDTO();
                empDTO.setEmpName(name);
                empDTO.setEmpNo(no);
                empDTO.setEmail(email);
                empDTO.setPhone(phone);
                empDTO.setDeptCode(deptCode);
                empDTO.setJobCode(jobCode);
                empDTO.setSalLevel(salLevel);
                empDTO.setSalary(sal);
                empDTO.setBonus(bonus);
                empDTO.setManagerId(manage);
                empDTO.setHireDate(Date.valueOf(date));
                empDTO.setEntDate(Date.valueOf(date2));
                empDTO.setEntYn(eYn);

                pstmt = con.prepareStatement(query);
                pstmt.setString(1, empDTO.getEmpName());
                pstmt.setString(2, empDTO.getEmpNo());
                pstmt.setString(3, empDTO.getEmail());
                pstmt.setString(4, empDTO.getPhone());
                pstmt.setString(5, empDTO.getDeptCode());
                pstmt.setString(6, empDTO.getJobCode());
                pstmt.setString(7, empDTO.getSalLevel());
                pstmt.setInt(8, empDTO.getSalary());
                pstmt.setDouble(9, empDTO.getBonus());
                pstmt.setString(10, empDTO.getManagerId());
                pstmt.setDate(11, empDTO.getHireDate());
                pstmt.setDate(12, empDTO.getEntDate());
                pstmt.setString(13, empDTO.getEntYn());

                result = pstmt.executeUpdate();
            }catch (IOException e) {
                throw new RuntimeException(e);
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                close(con);
                close(pstmt);
            }
            if (result > 0){
                System.out.println("직원 등록에 성공하였습니다.");
            } else System.out.println("직원 등록에 실패하였습니다.");
        }
}
