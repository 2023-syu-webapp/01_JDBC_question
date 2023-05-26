package run;

public class insertEmployeeInfo {

    // 사원 정보 전체를 입력받아 DTO객체에 담아서 insert
    // insert 성공하면 "직원 등록에 성공하였습니다." 출력
    // insert 실패하면 "직원 등록에 실패하였습니다." 출력

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        EmployeeDTO emp = new EmployeeDTO();
        Scanner sc = new Scanner(System.in);

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/menu-query.xml"));
            String query = prop.getProperty("insertEmployee");

            System.out.print("사번을 입력하세요 : ");
            String empId = sc.nextLine();
            System.out.print("이름을 입력하세요 : ");
            String empName = sc.nextLine();
            System.out.print("주민번호를 입력하세요 : ");
            String empNo = sc.nextLine();
            System.out.print("이메일을 입력하세요 : ");
            String email = sc.nextLine();
            System.out.print("전화번호를 입력하세요 : ");
            String phone = sc.nextLine();
            System.out.print("부서코드를 입력하세요 : ");
            String deptCode = sc.nextLine();
            System.out.print("직급코드를 입력하세요 : ");
            String jobCode = sc.nextLine();
            System.out.print("급여레벨을 입력하세요 : ");
            String salLevel = sc.nextLine();
            System.out.print("급여를 입력하세요 : ");
            Int salary = sc.nextInt();
            System.out.print("보너스를 입력하세요 : ");
            Double bonus = sc.nextDouble();
            System.out.print("매니저번호를 입력하세요 : ");
            String managerId = sc.nextLine();
            System.out.print("입사일을 입력하세요 : ");
            Date hireDate = Date.valueOf(sc.nextLine());
            System.out.print("퇴사일을 입력하세요 : ");
            Date entDate = Date.valueOf(sc.nextLine());
            System.out.print("퇴직여부를 입력하세요 : ");
            String entYn = sc.nextLine();

            con = new DBConnect().getConn();

            pstmt = con.prepareStatement(query);
            result = pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

        if(result > 0) {
            System.out.println("직원 등록에 성공하였습니다.");
        } else {
            System.out.println("직원 등록에 실패하였습니다.");
        }
    }
}
