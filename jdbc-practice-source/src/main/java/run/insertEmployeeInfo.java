package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class insertEmployeeInfo {
    public static void main(String[] args) {

        Connection con = getConnection();
        EmployeeDTO empdto = null;
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("insertEmployee");
            System.out.println(query);

            Scanner sc = new Scanner(System.in);
            System.out.println("추가할 직원의 아이디를 입력하세요 :");
            String newId = sc.next();
            System.out.println("추가할 직원의 이름을 입력하세요 :");
            String newName = sc.next();
            System.out.println("추가할 직원의 주민번호를 입력하세요 : ( 6자리-7자리)");
            String newNo = sc.next();
            System.out.println("추가할 직원의 이메일을 입력하세요:");
            String newEmail = sc.next();
            System.out.println("추가할 직원의 전화번호를 입력하세요:");
            String newPhone = sc.next();
            System.out.println("추가할 직원의 부서번호를 입력하세요");
            String newDetono = sc.next();
            System.out.println("추가할 직원의 직급을 입력하세요:");
            String newjobcode = sc.next();
            System.out.println("추가할 직원의 sal_level을 입력하세요 :");
            String newsallevel = sc.next();
            System.out.println("추가할 직원의 급여를 입력하세요");
            int newsalary = sc.nextInt();
            System.out.println("추가할 직원의 보너스를 입력해주세요");
            Double newbonus = sc.nextDouble();
            System.out.println("추가할 직원의 사수번호를 입력주세요:");
            String newManagerid = sc.next();

            System.out.println("추가할 직원의 고용일을 입력하세요(yyyy-MM-dd):");
            String newhiredate = sc.next();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(newhiredate, dateTimeFormatter);

            System.out.println("추가할 직원의 ENT날짜를 입력해주세요(yyyy-MM-dd)");
            String newentdate = sc.next();
            dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate entdate = LocalDate.parse(newentdate, dateTimeFormatter);

            System.out.println("ENT 여부를 입력해주세요 (N/Y) :");
            String newEntny = sc.next();


            empdto = new EmployeeDTO();
            empdto.setEmpId(newId);
            empdto.setEmpName(newName);
            empdto.setEmpNo(newNo);
            empdto.setEmail(newEmail);
            empdto.setPhone(newPhone);
            empdto.setDeptCode(newDetono);
            empdto.setJobCode(newjobcode);
            empdto.setSallevel(newsallevel);
            empdto.setSalary(newsalary);
            empdto.setBonus(newbonus);
            empdto.setManagerId(newManagerid);
            empdto.setHireDate(Date.valueOf(date));
            empdto.setEntDate(Date.valueOf(entdate));
            empdto.setEntYn(newEntny);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1,empdto.getEmpId());
            pstmt.setString(2,empdto.getEmpName());
            pstmt.setString(3,empdto.getEmpNo());
            pstmt.setString(4,empdto.getEmail());
            pstmt.setString(5,empdto.getPhone());
            pstmt.setString(6, empdto.getDeptCode());
            pstmt.setString(7,empdto.getJobCode());
            pstmt.setString(8,empdto.getSallevel());
            pstmt.setInt(9,empdto.getSalary());
            pstmt.setDouble(10, empdto.getBonus());
            pstmt.setString(11,empdto.getManagerId());
            pstmt.setDate(12, empdto.getHireDate());
            pstmt.setDate(13, empdto.getEntDate());
            pstmt.setString(14, empdto.getEntYn());

            result = pstmt.executeUpdate();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("직원 등록에 성공하셨습니다.");
        } else {
            System.out.println("직원 등록에 실패하였습니다.");
        }

    }
}
