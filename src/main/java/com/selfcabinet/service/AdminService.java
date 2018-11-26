package com.selfcabinet.service;

import com.selfcabinet.constant.ResponseMessage;
import com.selfcabinet.mapper.AdminMapper;
import com.selfcabinet.mapper.CabinetMapper;
import com.selfcabinet.model.Cabinet;
import com.selfcabinet.model.SelfCabinetException;
import com.selfcabinet.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminMapper adminMapper;
    private final CabinetMapper cabinetMapper;

    @Autowired
    public AdminService(AdminMapper adminMapper,CabinetMapper cabinetMapper){
        this.adminMapper=adminMapper;
        this.cabinetMapper=cabinetMapper;
    }

    //登录
    public String login(Admin admin)throws Exception{
        if (admin.getAdmin_id()==null){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.MISSED_ADMIN_ID,ResponseMessage.MISSED_ADMIN_ID);
        }
        Admin adminFromDb=adminMapper.getById(admin.getAdmin_id());
        if (adminFromDb.getPassword().equals(admin.getPassword())){
            if (adminFromDb.getBar_id().equals(admin.getBar_id())){
                adminMapper.updateStateById(Admin.LOGIN,admin.getAdmin_id());
                return ResponseMessage.SUCCESS_REGISTER;
            }
            else {
                throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR_BAR,ResponseMessage.ERROR_BAR);
            }
        }
        else {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR_PASSWORD,ResponseMessage.ERROR_PASSWORD);
        }

    }

    //更新密码
    public String updatePassword(Admin admin,String newPassword){
        if (admin.getAdmin_id()==null){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.MISSED_ADMIN_ID,ResponseMessage.MISSED_ADMIN_ID);
        }
        if (newPassword==null){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(),ResponseMessage.MISSED_NEW_PASSWORD,ResponseMessage.MISSED_NEW_PASSWORD);
        }
        Admin adminFromDb=adminMapper.getById(admin.getAdmin_id());
        if (adminFromDb.getState().equals(Admin.LOGOUT)){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.NO_LOGIN,ResponseMessage.NO_LOGIN);
        }
        if (adminFromDb.getPassword().equals(admin.getPassword())){
            adminMapper.updatePasswordById(newPassword,admin.getAdmin_id());
            admin.setPassword(newPassword);
            return  ResponseMessage.SUCCESS_UPDATE_PASSWORD;
        }
        else {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR_OLD_PASSWORD,ResponseMessage.ERROR_OLD_PASSWORD);
            //return  ResponseMessage.ERROR_OLD_PASSWORD;
        }
    }

    public void logout(String id){
        adminMapper.updateStateById(Admin.LOGOUT,id);
    }

    public List<Cabinet>showStatus(String admin_id,String cupboard_id){
        if (adminMapper.getStateById(admin_id).equals(Admin.LOGIN)){
           List<Cabinet> cabinetList=cabinetMapper.getByCupboardId(cupboard_id);
           return cabinetList;
        }
        else {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.NO_LOGIN,ResponseMessage.NO_LOGIN);
        }
    }

    public Cabinet openCabinet (String admin_id,String cupboard_id,String no){
        if (adminMapper.getStateById(admin_id).equals(Admin.LOGIN)){
            Cabinet cabinet=cabinetMapper.getByCupAndNo(cupboard_id,no);
            cabinet.setOpen(Cabinet.OPEN);
            return cabinet;
        }
        else {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.NO_LOGIN,ResponseMessage.NO_LOGIN);
        }
    }

    public List<Admin> queryAdmin(){
        return adminMapper.getAll();
    }
}

