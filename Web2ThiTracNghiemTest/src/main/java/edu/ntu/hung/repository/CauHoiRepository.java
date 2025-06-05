package edu.ntu.hung.repository;

import edu.ntu.hung.entity.CauHoi;
import edu.ntu.hung.entity.MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CauHoiRepository extends JpaRepository<CauHoi, Integer>
{
	// Tìm toàn bộ câu hỏi thuộc một môn học
	List<CauHoi> findByMonHoc(MonHoc monHoc);

	// Nếu cần tìm 1 câu hỏi cụ thể theo id thì dùng findById(id) đã có sẵn
}
