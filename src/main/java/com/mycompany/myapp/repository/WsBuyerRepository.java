package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WsBuyer;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data  repository for the WsBuyer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WsBuyerRepository extends JpaRepository<WsBuyer, Long> {

    List<WsBuyer> findAllByPhoneAndStatus(String phone, Boolean status);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "update ws_buyer set balance=balance+:money where phone=:phone", nativeQuery = true)
    int addBalance(@Param("phone") String phone, @Param("money") Float money);

    WsBuyer findByPhone(String phone);

    WsBuyer findByOpenid(String openid);
}
