package nk.honnur.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import nk.honnur.entity.PurchaseOrderPayments;

public interface PurchaseOrderPaymentsRepo extends JpaRepository<PurchaseOrderPayments, Integer>{

}
