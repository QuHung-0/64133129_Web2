package edu.ntu.hung.repository;

import edu.ntu.hung.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer>
{
	TaiKhoan findByTenDangNhap(String tenDangNhap);
}
