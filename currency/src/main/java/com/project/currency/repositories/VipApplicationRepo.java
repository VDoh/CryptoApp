package com.project.currency.repositories;

import com.project.currency.models.VipApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VipApplicationRepo extends JpaRepository<VipApplication, Long> {

   @Query(value = "SELECT * FROM vip_application JOIN user on vip_application.id_user = user.id_user", nativeQuery = true)
   List<VipApplication> find();
}
