package edu.ntu.hung.repository;

import edu.ntu.hung.entity.CauTraLoi;
import edu.ntu.hung.entity.CauHoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CauTraLoiRepository extends JpaRepository<CauTraLoi, Integer>
{
	// Tìm danh sách đáp án cho một câu hỏi
	List<CauTraLoi> findByCauHoi(CauHoi cauHoi);
}
