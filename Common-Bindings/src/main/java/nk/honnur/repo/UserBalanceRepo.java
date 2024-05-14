package nk.honnur.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import nk.honnur.entity.UserBalanceEntity;

public interface UserBalanceRepo extends JpaRepository<UserBalanceEntity, Integer>{

}
