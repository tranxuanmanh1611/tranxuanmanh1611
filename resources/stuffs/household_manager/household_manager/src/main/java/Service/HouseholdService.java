package Service;

import model.HouseHold;

import java.util.List;

public interface HouseholdService {
    List<HouseHold> getAll(int firstRecordIndex);
    String getMemberByHouse(String id);
    int getNumberOfPages(int recordInEachPage);
    void editHouseHold(String id,String owner,String createDate,String address);
    boolean ownerValidate(String owner);

}
