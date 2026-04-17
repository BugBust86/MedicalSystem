package lds.com.medicalsystem.user.service;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.common.utils.config.JWTUtil;
import lds.com.medicalsystem.user.VO.CardInfoVO;
import lds.com.medicalsystem.user.VO.UserInfoVO;
import lds.com.medicalsystem.user.DTO.MedicalCardAddDTO;
import lds.com.medicalsystem.user.DTO.MedicalCardUpdateDTO;
import lds.com.medicalsystem.user.VO.MedicalCardDetailVO;
import lds.com.medicalsystem.user.VO.PatientRecordDetailVO;
import lds.com.medicalsystem.user.VO.PatientRecordVO;
import lds.com.medicalsystem.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImp implements UserService{
    private final UserMapper userMapper;
    public UserServiceImp(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public void register(String phone, String password, String userName) {
        Boolean isTrue = userMapper.checkPhoneExists(phone);
        // 手机号存在返回true，if语句抛出已注册的异常
        if(isTrue){
            throw new BusinessException("该手机号已注册");
        }
        try {
            int i = userMapper.userRegister(phone, password, userName);
            if (i == 0){
                throw new BusinessException("注册失败,无数据插入");
            }
        } catch (Exception e) {
            throw new BusinessException("手机号为["+phone+"]的用户注册失败,底层执行异常", e);
        }
    }
    @Override
    public ResultVO<String> login(String phone,String password){
        Boolean isTrue = userMapper.checkPhoneExists(phone);
        if(!isTrue){
            // 手机号不存在，请先注册
            throw new BusinessException("手机号不存在，请先注册");
        }
        String psw = userMapper.userLoginSelect(phone);
        int userId = userMapper.findIdByPhone(phone);
        if(psw.equals(password)){
            Map<String,Object> claims = new HashMap<>();
            // 通过手机号、userId获得token
            claims.put("phone",phone);
            claims.put("userId",userId);
            String token = JWTUtil.genToke(claims);
            return ResultVO.success("登录成功",token);
        }
        System.out.println(psw);    // 如果登录失败打印控制台判断原因
        System.out.println(password);
        return ResultVO.error("密码错误,登录失败");
    }
    @Override
    public void addMedicalCard(MedicalCardAddDTO dto) {
        // 调用Mapper层添加就诊卡,一个患者可以被多个亲属注册患者卡，所以不用校验身份证是否被注册就诊卡
        int num = userMapper.addMedicalCard(dto);
        if (num == 0){
            throw new BusinessException("添加就诊卡失败");
        }
    }
    @Override
    public UserInfoVO showUserInfo(int userId) {
        try {
            return userMapper.selectByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("sql查询异常"+e);
        }
    }
    @Override
    public List<CardInfoVO> searchCardList(int userId) {
        try {
            return userMapper.searchCardList(userId);
        } catch (Exception e) {
            throw new RuntimeException("sql查询异常"+e);
        }
    }
    @Override
    public MedicalCardDetailVO showMedicalCard(int cardId) {
        try {
            return userMapper.selectMedicalCardById(cardId);
        } catch (Exception e) {
            throw new RuntimeException("sql查询异常"+e);
        }
    }
    @Override
    public void updateMedicalCard(MedicalCardUpdateDTO dto) {
        int num = userMapper.updateMedicalCard(dto);
        if (num == 0){
            throw new BusinessException("修改就诊卡失败");
        }
    }
    @Override
    public List<PatientRecordVO> getRecordList(int userId) {
        try {
            return userMapper.selectRecordListByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("sql查询异常"+e);
        }
    }
    @Override
    public PatientRecordDetailVO getRecordDetail(int recordId) {
        try {
            return userMapper.selectRecordDetailById(recordId);
        } catch (Exception e) {
            throw new RuntimeException("sql查询异常"+e);
        }
    }
}
