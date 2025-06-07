package edu.ntu.hung.controller;

import edu.ntu.hung.entity.CauHoi;
import edu.ntu.hung.entity.CauTraLoi;
import edu.ntu.hung.entity.MonHoc;
import edu.ntu.hung.repository.CauHoiRepository;
import edu.ntu.hung.repository.CauTraLoiRepository;
import edu.ntu.hung.repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/giaovien/question") // Tất cả đường dẫn bắt đầu bằng /giaovien/question
public class TeacherQuestionController
{

	@Autowired
	private MonHocRepository monHocRepo;

	@Autowired
	private CauHoiRepository cauHoiRepo;

	@Autowired
	private CauTraLoiRepository cauTraLoiRepo;

	// ===== 1. Xem danh sách câu hỏi của một môn (GET) =====
	// Ví dụ: /giaovien/question/list?monHocId=3
	@GetMapping("/list")
	public String listQuestions(@RequestParam("monHocId") Integer monHocId, Model model)
	{
		MonHoc monHoc = monHocRepo.findById(monHocId).orElse(null);
		if (monHoc == null)
		{
			return "redirect:/giaovien/subject/list";
		}
		List<CauHoi> dsCau = cauHoiRepo.findByMonHoc(monHoc);
		model.addAttribute("monHoc", monHoc);
		model.addAttribute("dsCauHoi", dsCau);
		return "giaovien/question_list"; // -> templates/giaovien/question_list.html
	}

	// ===== 2. Hiển thị form TẠO mới hoặc SỬA câu hỏi (GET) =====
	// Tạo mới: /giaovien/question/form?monHocId=3
	// Sửa: /giaovien/question/form?monHocId=3&cauHoiId=7
	@GetMapping("/form")
	public String showQuestionForm(@RequestParam("monHocId") Integer monHocId,
			@RequestParam(value = "cauHoiId", required = false) Integer cauHoiId, Model model)
	{
		MonHoc monHoc = monHocRepo.findById(monHocId).orElse(null);
		if (monHoc == null)
		{
			return "redirect:/giaovien/subject/list";
		}

		if (cauHoiId == null)
		{
			// Tạo mới câu hỏi
			// Tạo 1 đối tượng CauHoi rỗng và 4 CauTraLoi rỗng cho form
			CauHoi chNew = new CauHoi();
			chNew.setMonHoc(monHoc);

			// Tạo 4 đối tượng CauTraLoi tạm, chưa set nội dung, để form hiển thị 4 editor
			List<CauTraLoi> dsTraLoiTemp = new ArrayList<>();
			for (int i = 0; i < 4; i++)
			{
				dsTraLoiTemp.add(new CauTraLoi());
			}

			model.addAttribute("monHoc", monHoc);
			model.addAttribute("cauHoi", chNew);
			model.addAttribute("dsTraLoiTemp", dsTraLoiTemp);
			model.addAttribute("isEdit", false);
		} else
		{
			// Sửa câu hỏi: load sẵn CauHoi và 4 CauTraLoi từ DB
			CauHoi chEdit = cauHoiRepo.findById(cauHoiId).orElse(null);
			if (chEdit == null)
			{
				return "redirect:/giaovien/question/list?monHocId=" + monHocId;
			}
			List<CauTraLoi> dsTraLoiExist = cauTraLoiRepo.findByCauHoi(chEdit);

			model.addAttribute("monHoc", monHoc);
			model.addAttribute("cauHoi", chEdit);
			model.addAttribute("dsTraLoiTemp", dsTraLoiExist);
			model.addAttribute("isEdit", true);
		}

		return "giaovien/question_form"; // -> templates/giaovien/question_form.html
	}

	// ===== 3. Xử lý LƯU (CREATE or UPDATE) câu hỏi và 4 đáp án (POST) =====
	@PostMapping("/save")
	public String saveQuestion(@RequestParam("monHocId") Integer monHocId,
			@RequestParam(value = "cauHoiId", required = false) Integer cauHoiId,
			@RequestParam("noiDungCauHoi") String noiDungCauHoi, @RequestParam("dapAn1") String dapAn1,
			@RequestParam("dapAn2") String dapAn2, @RequestParam("dapAn3") String dapAn3,
			@RequestParam("dapAn4") String dapAn4, @RequestParam("dapAnDung") Integer soThuTuDapAnDung, Model model)
	{

		MonHoc monHoc = monHocRepo.findById(monHocId).orElse(null);
		if (monHoc == null)
		{
			return "redirect:/giaovien/subject/list";
		}

		CauHoi cauHoi;
		if (cauHoiId == null)
		{
			// Tạo mới
			cauHoi = new CauHoi();
			cauHoi.setMonHoc(monHoc);
		} else
		{
			// Sửa: tải từ DB
			cauHoi = cauHoiRepo.findById(cauHoiId).orElse(new CauHoi());
			cauHoi.setMonHoc(monHoc);
		}
		// 1) Set nội dung câu hỏi
		cauHoi.setNoiDungCauHoi(noiDungCauHoi);
		// 2) Lưu cauHoi để có ID
		cauHoiRepo.save(cauHoi);

		// 3) XỬ LÝ 4 đáp án
		if (cauHoiId != null)
		{
			// Xóa tất cả đáp án cũ của câu hỏi này
			List<CauTraLoi> dsOld = cauTraLoiRepo.findByCauHoi(cauHoi);
			for (CauTraLoi da : dsOld)
			{
				cauTraLoiRepo.delete(da);
			}
		}

		// 4) Tạo mới 4 đối tượng CauTraLoi, đánh dấu đúng (Dung=true) cho đáp án đúng
		List<String> dsDapAn = List.of(dapAn1, dapAn2, dapAn3, dapAn4);
		for (int i = 0; i < 4; i++)
		{
			CauTraLoi ct = new CauTraLoi();
			ct.setCauHoi(cauHoi);
			ct.setNoiDungTraLoi(dsDapAn.get(i));
			ct.setDung((i + 1) == soThuTuDapAnDung);
			cauTraLoiRepo.save(ct);
		}

		// 5) Sau khi lưu xong, quay về danh sách câu hỏi cho môn
		return "redirect:/giaovien/question/list?monHocId=" + monHocId;
	}

	// ===== 4. Xóa một câu hỏi (GET) =====
	@GetMapping("/delete")
	public String deleteQuestion(@RequestParam("cauHoiId") Integer cauHoiId, @RequestParam("monHocId") Integer monHocId)
	{
		// Xóa hết đáp án trước để tránh constraint
		CauHoi ch = cauHoiRepo.findById(cauHoiId).orElse(null);
		if (ch != null)
		{
			List<CauTraLoi> dsTraLoi = cauTraLoiRepo.findByCauHoi(ch);
			for (CauTraLoi da : dsTraLoi)
			{
				cauTraLoiRepo.delete(da);
			}
			// Rồi xóa câu hỏi
			cauHoiRepo.delete(ch);
		}
		return "redirect:/giaovien/question/list?monHocId=" + monHocId;
	}
}
