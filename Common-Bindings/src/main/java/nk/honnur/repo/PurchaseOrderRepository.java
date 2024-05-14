package nk.honnur.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import nk.honnur.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer>{

}
