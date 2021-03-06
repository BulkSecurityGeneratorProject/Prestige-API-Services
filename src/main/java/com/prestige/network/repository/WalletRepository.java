package com.prestige.network.repository;

import com.prestige.network.domain.User;
import com.prestige.network.domain.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Wallet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("select wallet from Wallet wallet where wallet.user.login = ?#{principal.username}")
    List<Wallet> findByUserIsCurrentUser();
    Page<Wallet> findByUserOrderById(User user, Pageable pageable);
    List<Wallet> findByUserAndAddress(User user,String address);
    List<Wallet> findByUserOrderById(User user);

}
