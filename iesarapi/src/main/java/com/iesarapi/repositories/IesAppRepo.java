package com.iesarapi.repositories;

import com.iesarapi.entites.IesAppsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IesAppRepo extends JpaRepository<IesAppsEntity,Integer> {


    public List<IesAppsEntity> fetchUserApps();

    @Query(value = "from IesAppsEntity where userId=:userId")
    public List<IesAppsEntity> fetchCwApps(Integer userId);

    //that is one example and we can go for query by example also
}
