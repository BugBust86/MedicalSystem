package lds.com.medicalsystem.staff.doctor.service;

import lds.com.medicalsystem.common.utils.config.ThreadLocalUtil;
import lds.com.medicalsystem.staff.doctor.VO.DoctorWorkTableVO;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorPersonalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DoctorPersonalServiceImp implements DoctorPersonalService {
    @Autowired
    private DoctorPersonalMapper doctorPersonalMapper;
    // 传入医生工号，返回值班日期、时间段、工号、最大预约人数、已预约人数
    @Override
    public List<DoctorWorkTableVO> searchDocWorkTable(String doctorNo) {
        try {
            return doctorPersonalMapper.searchDoctorWorkTable(doctorNo);
        } catch (Exception e) {
            throw new RuntimeException("查询失败",e);
        }
    }

    @Override
    public void update(String email, String specialty,String phone) {
        Map<String,Object> map = ThreadLocalUtil.get();
        String doctorNo = (String)map.get("工号");
        if(doctorPersonalMapper.update(doctorNo,email,specialty,phone)!=1){
            throw new RuntimeException("修改更新失败");
        }
    }
}