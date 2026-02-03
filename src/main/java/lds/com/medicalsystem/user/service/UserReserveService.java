package lds.com.medicalsystem.user.service;

import lds.com.medicalsystem.user.DTO.UserReserveDTO;
import lds.com.medicalsystem.user.DTO.UserReserveRequest;

public interface UserReserveService {
    // 根据用户选择的医生、日期、时间段定位值班表，对值班表的reserved+1
    void userReserve(UserReserveRequest request);
}
