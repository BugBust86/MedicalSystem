package lds.com.medicalsystem.user.service;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.user.VO.CardInfoVO;
import lds.com.medicalsystem.user.VO.UserInfoVO;
import lds.com.medicalsystem.user.DTO.MedicalCardAddDTO;
import lds.com.medicalsystem.user.DTO.MedicalCardUpdateDTO;
import lds.com.medicalsystem.user.VO.MedicalCardDetailVO;
import lds.com.medicalsystem.user.VO.PatientRecordDetailVO;
import lds.com.medicalsystem.user.VO.PatientRecordVO;

import java.util.List;

public interface UserService {
    // 用户注册
    void register(String phone, String password, String userName);
    // 用户登录
    ResultVO<String> login(String phone,String password);
    // 改为根据userId返回用户信息
    UserInfoVO showUserInfo(int userId);
    // 用户添加就诊卡
    void addMedicalCard(MedicalCardAddDTO dto);
    // 查看就诊卡列表
    List<CardInfoVO> searchCardList(int userId);
    // 查看就诊卡详情
    MedicalCardDetailVO showMedicalCard(int cardId);
    // 修改就诊卡
    void updateMedicalCard(MedicalCardUpdateDTO dto);
    // 查看就诊记录列表
    List<PatientRecordVO> getRecordList(int userId);
    // 查看就诊记录详情
    PatientRecordDetailVO getRecordDetail(int recordId);
}
