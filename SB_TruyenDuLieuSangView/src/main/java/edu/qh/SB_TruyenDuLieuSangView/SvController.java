package edu.qh.SB_TruyenDuLieuSangView;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SvController 
{   
    @GetMapping("/SinhVien")
    public String SinhVien(Model model) 
    {
        model.addAttribute("mssv", "64133129");
        model.addAttribute("hoten", "NGUYỄN LỮ QUỐC HƯNG");
        model.addAttribute("namsinh", 2004);
        model.addAttribute("gioitinh", "Nam");
        
        return "SinhVien";
    }
}
