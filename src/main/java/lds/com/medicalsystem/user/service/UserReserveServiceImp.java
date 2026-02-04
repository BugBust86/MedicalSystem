package lds.com.medicalsystem.user.service;

import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.user.DTO.UserReserveDTO;
import lds.com.medicalsystem.user.DTO.UserReserveRequest;
import lds.com.medicalsystem.user.mapper.UserReserveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserReserveServiceImp implements UserReserveService {
    @Autowired
    private UserReserveMapper userReserveMapper;
    @Override
    public void userReserve(UserReserveRequest request) {
        UserReserveDTO dto = new UserReserveDTO();
        dto.setDoctorNo(userReserveMapper.selectIdByName(request.getDoctorName()));
        // 先查reserve_empty是否等于0，等于0说明没有空位不允许预约
        if(userReserveMapper.queryReserveEmpty(dto)==0){
            throw new BusinessException("已预约满，暂无空位");
        }
        // 再更新reserved
        userReserveMapper.updateReserved(dto);
    }

    @Override
    public void userReserveCheckItem(int itemId) {
        int i = 0;
        try {
            i = userReserveMapper.updateCheckItemReserved(itemId);
        } catch (Exception e) {
            throw new RuntimeException("用户预约失败",e);
        }
        if(i!=1){
            System.out.println(i);
            throw new BusinessException("受影响的行数不为1，更新失败");
        }
    }
}
