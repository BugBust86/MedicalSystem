package lds.com.medicalsystem.user.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.user.DTO.UserReserveRequest;
import lds.com.medicalsystem.user.service.UserReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 用户的预约挂号模块
@RestController
@RequestMapping("/user")
public class UserReserveController {
    @Autowired
    private UserReserveService userReserveService;
    // 预约选择医生的接口
    @PostMapping("/appointment")
    public ResultVO<Void> prebookDoctor(@RequestBody UserReserveRequest request){
        userReserveService.userReserve(request);
        return ResultVO.success("预约成功");
    }
    // 选择体检化验项目的接口,传入项目id，内部对check_items的reserved+1
    @PatchMapping("/checkItem")
    public ResultVO<Void> checkItem(int itemId){
        userReserveService.userReserveCheckItem(itemId);
        return ResultVO.success("用户预约检查化验项目成功");
    }
}
