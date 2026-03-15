package lds.com.medicalsystem.common.MVC;

import lds.com.medicalsystem.common.DTO.InnerLoginDTO;
import lds.com.medicalsystem.common.DTO.InnerRegisterDTO;
import lds.com.medicalsystem.common.DTO.UpdatePswDTO;
import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.VO.StaffInformationVO;
import lds.com.medicalsystem.common.utils.config.ThreadLocalUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
// @Validated会对参数、类等内部所有带有校验注解（如 @NotBlank、@NotNull、@Size）的字段进行校验
@RequestMapping("/staff")     // 接口公共的最初请求路径
public class StaffController {
    private final StaffService staffService;
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // 员工注册自己已有账号的密码
    @PostMapping("/registerBySelf")
    public ResultVO<Void> staffRegisterBySelf(@Validated @RequestBody InnerRegisterDTO dto) {
        staffService.staffRegisterBySelf(dto);
        return ResultVO.success("注册成功");
    }

    // 员工登录自己已有账号
    @PostMapping("/staffLogin")
    public ResultVO<Map<String, String>> staffLogin(@RequestBody InnerLoginDTO dto){
        return staffService.staffLogin(dto);
    }

    // 员工查看个人中心
    @GetMapping("/staffInfo")
    public ResultVO<StaffInformationVO> staffInfo(){
        // 请求头传入token，先在拦截器解析，成功后
        // 通过ThreadUtil的get获取工号和role（因为是通过工号和role登录的）
        Map<String,Object> map = ThreadLocalUtil.get();
        String staffId = (String)map.get("工号");
        String role = (String)map.get("role");
        StaffInformationVO vo = staffService.staffInfo(staffId, role);
        return ResultVO.success(vo);
    }

    // 员工修改密码
    @PostMapping("/updatePsw")
    public ResultVO<Void> updateStaffPsw(UpdatePswDTO dto){
        Map<String,Object> map = ThreadLocalUtil.get();
        String role = (String) map.get("role");
        String staffId = (String) map.get("工号");
        dto.setRole(role);
        dto.setStaffId(staffId);
        try {
            staffService.staffUpdatePsw(dto);
            return ResultVO.success("修改成功");
        } catch (Exception e) {
            throw new RuntimeException("修改失败："+e);
        }
    }
}
