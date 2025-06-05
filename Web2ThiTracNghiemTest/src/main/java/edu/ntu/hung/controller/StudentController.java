package edu.ntu.hung.controller;

import edu.ntu.hung.entity.CauHoi;
import edu.ntu.hung.entity.CauTraLoi;
import edu.ntu.hung.entity.MonHoc;
import edu.ntu.hung.entity.QuizResultItem;
import edu.ntu.hung.entity.SubjectColor; // <-- import đúng package
import edu.ntu.hung.repository.CauHoiRepository;
import edu.ntu.hung.repository.CauTraLoiRepository;
import edu.ntu.hung.repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hocsinh")
public class StudentController
{

	@Autowired
	private MonHocRepository monHocRepo;

	@Autowired
	private CauHoiRepository cauHoiRepo;

	@Autowired
	private CauTraLoiRepository cauTraLoiRepo;

	/**
	 * Mảng các lớp CSS màu luân phiên (Bootstrap background classes). Controller sẽ
	 * chọn colorClass = COLOR_CLASSES[i % COLOR_CLASSES.length].
	 */
	private static final String[] COLOR_CLASSES = { "bg-primary", "bg-success", "bg-warning", "bg-danger", "bg-info",
			"bg-secondary" };

	// ===== 1. Chọn môn để làm quiz =====
	@GetMapping("/choose-subject")
	public String showChooseSubject(Model model)
	{
		// 1. Lấy danh sách tất cả môn học
		List<MonHoc> dsMonHoc = monHocRepo.findAll();

		// 2. Tạo List<SubjectColor> để đính kèm màu cho mỗi môn
		List<SubjectColor> dsWithColors = new ArrayList<>();
		for (int i = 0; i < dsMonHoc.size(); i++)
		{
			MonHoc mh = dsMonHoc.get(i);
			// Chọn màu luân phiên dựa trên index
			String color = COLOR_CLASSES[i % COLOR_CLASSES.length];
			dsWithColors.add(new SubjectColor(mh, color));
		}

		// 3. Đẩy xuống Thymeleaf
		model.addAttribute("dsWithColors", dsWithColors);
		return "hocsinh/choose_subject"; // file templates/hocsinh/choose_subject.html
	}

	// ===== 2. Hiển thị form làm quiz =====
	@GetMapping("/take-quiz")
	public String showQuizForm(@RequestParam("monHocId") Integer monHocId, Model model)
	{
		MonHoc monHoc = monHocRepo.findById(monHocId).orElse(null);
		if (monHoc == null)
		{
			return "redirect:/hocsinh/choose-subject";
		}
		List<CauHoi> dsTatCaCauHoi = cauHoiRepo.findByMonHoc(monHoc);
		Collections.shuffle(dsTatCaCauHoi);
		int soCau = Math.min(10, dsTatCaCauHoi.size());
		List<CauHoi> ds10Cau = dsTatCaCauHoi.subList(0, soCau);

		model.addAttribute("monHoc", monHoc);
		model.addAttribute("dsCauHoi", ds10Cau);

		// Chuỗi ẩn danh sách ID
		List<Integer> dsId = new ArrayList<>();
		for (CauHoi ch : ds10Cau)
		{
			dsId.add(ch.getCauHoiId());
		}
		String danhSachId = String.join(",", dsId.stream().map(Object::toString).toList());
		model.addAttribute("danhSachId", danhSachId);

		// Đẩy repository để gọi vào template
		model.addAttribute("cauTraLoiRepo", cauTraLoiRepo);
		return "hocsinh/quiz_form";
	}

	// ===== 3. Xử lý khi Học sinh nộp bài =====
	@PostMapping("/submit-quiz")
	public String submitQuiz(@RequestParam("danhSachId") String danhSachId,
			@RequestParam Map<String, String> tatCaParams, Model model)
	{
		// 1. Tách chuỗi "1,5,7,..." thành List<Integer> listId
		String[] arrId = danhSachId.split(",");
		List<Integer> listId = new ArrayList<>();
		for (String s : arrId)
		{
			try
			{
				listId.add(Integer.parseInt(s));
			} catch (Exception e)
			{
				// nếu parse lỗi thì bỏ qua
			}
		}

		// 2. Tạo danh sách để chứa kết quả mỗi câu
		List<QuizResultItem> ketQuaList = new ArrayList<>();
		int demDung = 0, demSai = 0;

		// 3. Duyệt qua từng câu hỏi (có ID = cauHoiId)
		for (Integer cauHoiId : listId)
		{
			// 3.1 Lấy đối tượng CauHoi
			CauHoi ch = cauHoiRepo.findById(cauHoiId).orElse(null);
			if (ch == null)
			{
				demSai++;
				continue;
			}

			// 3.2 Lấy param "cau_<cauHoiId>"
			String tenParam = "cau_" + cauHoiId;
			String dapAnChonId = tatCaParams.get(tenParam);

			// 3.3 Tìm đối tượng CauTraLoi ứng với đáp án học sinh chọn
			CauTraLoi daChonObj = null;
			String noiDungDaChon = "";
			if (dapAnChonId != null)
			{
				try
				{
					int idDa = Integer.parseInt(dapAnChonId);
					daChonObj = cauTraLoiRepo.findById(idDa).orElse(null);
					if (daChonObj != null)
					{
						noiDungDaChon = daChonObj.getNoiDungTraLoi();
					}
				} catch (NumberFormatException e)
				{
					// coi như không chọn, giữ rỗng
				}
			}

			// 3.4 Tìm đáp án đúng cho câu hỏi hiện tại
			List<CauTraLoi> dsTraLoi = cauTraLoiRepo.findByCauHoi(ch);
			String noiDungDapAnDung = "";
			for (CauTraLoi da : dsTraLoi)
			{
				if (da.getDung() != null && da.getDung())
				{
					noiDungDapAnDung = da.getNoiDungTraLoi();
					break;
				}
			}

			// 3.5 Kiểm tra học sinh chọn đúng hay sai
			boolean dung = false;
			if (daChonObj != null && daChonObj.getDung() != null && daChonObj.getDung())
			{
				dung = true;
				demDung++;
			} else
			{
				demSai++;
			}

			// 3.6 Tạo QuizResultItem
			QuizResultItem item = new QuizResultItem(ch, noiDungDaChon, noiDungDapAnDung, dung);
			ketQuaList.add(item);
		}

		// 4. Đẩy kết quả ra Model
		model.addAttribute("demDung", demDung);
		model.addAttribute("demSai", demSai);
		model.addAttribute("ketQuaList", ketQuaList);

		return "hocsinh/quiz_result";
	}
}
