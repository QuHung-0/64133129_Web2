package edu.ntu.hung.controller;

import edu.ntu.hung.entity.TaiKhoan;
import edu.ntu.hung.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController
{

	@Autowired
	private TaiKhoanRepository taiKhoanRepo;

	// ===== Trang ĐĂNG KÝ (GET) =====
	@GetMapping("/register")
	public String showRegisterForm(Model model)
	{
		model.addAttribute("taiKhoan", new TaiKhoan());
		// Đổi trả về "auth/register" vì file nằm ở templates/auth/register.html
		return "auth/register";
	}

	// ===== Xử lý ĐĂNG KÝ (POST) =====
	@PostMapping("/register")
	public String processRegister(@ModelAttribute("taiKhoan") TaiKhoan taiKhoan, Model model)
	{
		// 1. Kiểm tra xem tên đăng nhập đã tồn tại chưa
		TaiKhoan existUser = taiKhoanRepo.findByTenDangNhap(taiKhoan.getTenDangNhap());
		if (existUser != null)
		{
			model.addAttribute("error", "Tên đăng nhập đã được sử dụng. Vui lòng chọn tên khác!");
			// Nếu có lỗi, cũng phải hiển thị lại template auth/register.html
			return "auth/register";
		}
		// 2. Lưu tài khoản mới (chưa mã hóa mật khẩu)
		taiKhoanRepo.save(taiKhoan);
		model.addAttribute("message", "Đăng ký thành công! Bạn có thể đăng nhập ngay.");
		// Sau khi đăng ký, chuyển sang trang login (templates/auth/login.html)
		return "auth/login";
	}

	// ===== Trang ĐĂNG NHẬP (GET) =====
	@GetMapping("/login")
	public String showLoginForm()
	{
		// Trả về auth/login vì file login.html nằm ở templates/auth/login.html
		return "auth/login";
	}

	// ===== Xử lý ĐĂNG NHẬP (POST) =====
	@PostMapping("/login")
	public String processLogin(@RequestParam("tenDangNhap") String tenDangNhap, @RequestParam("matKhau") String matKhau,
			Model model)
	{
		// 1. Tìm tài khoản theo tên đăng nhập
		TaiKhoan user = taiKhoanRepo.findByTenDangNhap(tenDangNhap);
		if (user == null)
		{
			model.addAttribute("error", "Không tìm thấy tên đăng nhập này!");
			// Nếu lỗi, hiển thị lại auth/login
			return "auth/login";
		}
		// 2. So sánh mật khẩu (lưu thẳng, không mã hóa)
		if (!user.getMatKhau().equals(matKhau))
		{
			model.addAttribute("error", "Mật khẩu không đúng!");
			return "auth/login";
		}
		// 3. Kiểm tra vai trò và chuyển hướng sang trang phù hợp
		if (user.getVaiTro().equals("GIAO_VIEN"))
		{
			// Chuyển đến trang danh sách môn học của giáo viên (đúng là
			// /giaovien/subject/list)
			return "redirect:/giaovien/subject/list";
		} else
		{
			return "redirect:/hocsinh/choose-subject";
		}

	}
}
