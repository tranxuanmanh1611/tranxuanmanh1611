package Service;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.Compile;
import model.HouseHold;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HouseholdServiceImpl implements HouseholdService {
    private static final String url = "jdbc:mysql://localhost:3306/household_manager?useSSL=false";
    private static final String username = "root";
    private static final String pass = "123456789";
    private static final String driver = "com.mysql.jdbc.Driver";

    private Connection getConnection() {
        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, username, pass);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cn;
    }


    @Override
    public List<HouseHold> getAll(int firstRecordIndex) {
       final String sql_get_all_household = "select * from house limit ?,3";
       List<HouseHold> houseHoldList = new ArrayList<>();
       try(Connection cn = getConnection(); PreparedStatement stm = cn.prepareStatement(sql_get_all_household);){
           stm.setInt(1,firstRecordIndex);
           ResultSet rs = stm.executeQuery();
           while (rs.next()){
              String id = rs.getString(1);
              String owner = rs.getString(2);
              int member = rs.getInt(3);
              String createDate = rs.getString(4);
              String address = rs.getString(5);
              HouseHold houseHold =new HouseHold(id,owner,member,createDate,address);
               houseHold.setMemberInfo(getMemberByHouse(id));
              houseHoldList.add(houseHold);
          }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
        return houseHoldList;
    }


    @Override
    public String getMemberByHouse(String id) {
        final String sql_get_member_by_house = "select name from citizen where house_id=?";
        String memberInfo = "<p>";
        try(Connection cn=getConnection();PreparedStatement stm=cn.prepareStatement(sql_get_member_by_house)){
            stm.setString(1,id);
            ResultSet rs=stm.executeQuery();
            while (rs.next()){
                memberInfo += rs.getString(1);
                memberInfo += " <br>";
            }
            memberInfo+= "</p>";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return memberInfo;
    }

    @Override
    public int getNumberOfPages(int recordInEachPage) {
        String sql_count_record = "select count(id) from house";
        int numberOfRecord = 0;
        try (Connection cn = getConnection();PreparedStatement stm=cn.prepareStatement(sql_count_record);ResultSet rs=stm.executeQuery()){
            while (rs.next()){
                numberOfRecord = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (int)Math.ceil(numberOfRecord/recordInEachPage);
    }

    @Override
    public void editHouseHold(String id, String owner, String createDate, String address) {
        String sql_edit_household = "update house set owner=?,create_date=?,address=? where id=?";
        try (Connection cn=getConnection();PreparedStatement stm=cn.prepareStatement(sql_edit_household)){
            stm.setString(1,owner);
            stm.setString(2,createDate);
            stm.setString(3,address);
            stm.setString(4,id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean ownerValidate(String owner) {
        String regex = "[A-Z][a-z]+( [A-Z][a-z]+)*";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(owner);
        return matcher.matches();
    }


}
