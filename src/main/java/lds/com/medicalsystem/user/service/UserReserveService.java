package lds.com.medicalsystem.user.service;

import lds.com.medicalsystem.user.DTO.UserReserveRequest;

public interface UserReserveService {
    // 根据用户选择的医生、日期、时间段定位值班表，对值班表的reserved+1
    void userReserve(UserReserveRequest request);
    // 根据用户选择的化验项目，让对应的reserved+1
    void userReserveCheckItem(int itemId);
    // 用户查看某个医生的已预约人数，和剩余空位
}
