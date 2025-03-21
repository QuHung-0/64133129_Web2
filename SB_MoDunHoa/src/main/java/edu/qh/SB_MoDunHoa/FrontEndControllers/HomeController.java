package edu.qh.SB_MoDunHoa.FrontEndControllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.qh.SB_MoDunHoa.model.BanTin;

@Controller
public class HomeController
{

	@GetMapping("/")
	public String home(Model model)
	{
		List<BanTin> dsTin = new ArrayList<>();

		BanTin tin1 = new BanTin();
		tin1.setTieuDe(
				"Bách Hóa Xanh trở lại mở mới cửa hàng mỗi ngày");
		tin1.setNoiDung("Bách Hóa Xanh mở 94 cửa hàng trong hai tháng đầu năm, trung bình khai trương 1-2 điểm mỗi ngày, rầm rộ hơn đợt mở mới của cả năm 2024.");
		tin1.setAnhUrl("/images/trangchu/Tin1.jpg");
		dsTin.add(tin1);

		BanTin tin2 = new BanTin();
		tin2.setTieuDe("Đan Mạch 1-0 Bồ Đào Nha: Đội khách nhạt nhòa; Hojlund tỏa sáng");
		tin2.setNoiDung(
				"Đan Mạch đã có màn trình diễn đầy quả cảm để giành chiến thắng 1-0 trước Bồ Đào Nha trong trận lượt đi tứ kết UEFA Nations League trên sân Parken, Copenhagen.");
		tin2.setAnhUrl("/images/trangchu/Tin2.jpg");
		dsTin.add(tin2);

		model.addAttribute("dsTin", dsTin);
		return "frontEndView/index";
	}

	@GetMapping("/frontEndView/kinhdoanh")
	public String kinhDoanh()
	{
		return "frontEndView/kinhdoanh";
	}

	@GetMapping("/frontEndView/giaoduc")
	public String giaoDuc()
	{
		return "frontEndView/giaoduc";
	}

	@GetMapping("/frontEndView/giaitri")
	public String gaiTri()
	{
		return "frontEndView/giaitri";
	}

	@GetMapping("/frontEndView/thethao")
	public String theThao()
	{
		return "frontEndView/thethao";
	}

}
