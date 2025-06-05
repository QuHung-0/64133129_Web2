package edu.ntu.hung.repository;

import edu.ntu.hung.entity.MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonHocRepository extends JpaRepository<MonHoc, Integer>
{
	// Chẳng hạn cần tìm môn học theo tên, nếu cần:
	MonHoc findByTenMonHoc(String tenMonHoc);
}
