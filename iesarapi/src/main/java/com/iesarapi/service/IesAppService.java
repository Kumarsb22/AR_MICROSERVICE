package com.iesarapi.service;

import com.iesarapi.bindingclass.AppForm;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface IesAppService {

    public String createApp(AppForm appForm);

    public List<AppForm> fetchApps(Integer userId);

}
