package com.iesarapi.service;

import com.iesarapi.bindingclass.AppForm;
import com.iesarapi.constants.AppConstants;
import com.iesarapi.entites.IesAppsEntity;
import com.iesarapi.entites.UserEntity;
import com.iesarapi.exception.SsaWebException;
import com.iesarapi.repositories.IesAppRepo;
import com.iesarapi.repositories.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IesAppServiceImpl implements IesAppService{

    private  static  final String SSA_WEB_API_URL=" https://ssa.web.app/{ssn} ";

    private IesAppRepo iesAppRepo;

    private UserRepo userRepo;

    @Autowired
    public IesAppServiceImpl(IesAppRepo iesAppRepo,UserRepo userRepo){
        this.iesAppRepo=iesAppRepo;
        this.userRepo=userRepo;
    }

    @Override
    public String createApp(AppForm appForm) {
    try {
        WebClient webClient=WebClient.create();

       String stateName= webClient.get().uri(SSA_WEB_API_URL, appForm.getSsn())
                .retrieve()
                .bodyToMono(String.class)
                .block();

       if("RI".equals(stateName)){
           //valid citizen app
           IesAppsEntity iesAppsEntity = new IesAppsEntity();
           BeanUtils.copyProperties(appForm,iesAppsEntity);
           UserEntity userEntity = this.userRepo.findById(appForm.getUserId()).get();
           iesAppsEntity.setUser(userEntity);
        iesAppsEntity =  this.iesAppRepo.save(iesAppsEntity);
           return "Applicaton created with Case Number : "+ iesAppsEntity.getCaseNo();
       }
    }catch (Exception e){
     throw new SsaWebException(e.getMessage());
    }
        return "Invalid SSN";
    }

    @Override
    public List<AppForm> fetchApps(Integer userId) {
        UserEntity userEntity = userRepo.findById(userId).get();
        String roleName = userEntity.getRoleName();
        List<IesAppsEntity> iesAppsEntities=null;
        if("ADMIN".equals(roleName)){
             iesAppsEntities = this.iesAppRepo.fetchUserApps();
        }else {
            iesAppsEntities=this.iesAppRepo.fetchCwApps(userId);
        }
        List<AppForm> appForms=new ArrayList<AppForm>();
        for(AppForm af: appForms){
            AppForm appForm=new AppForm();
            BeanUtils.copyProperties(af,appForm);
            appForms.add(appForm);
        }
        return appForms;
    }
}
