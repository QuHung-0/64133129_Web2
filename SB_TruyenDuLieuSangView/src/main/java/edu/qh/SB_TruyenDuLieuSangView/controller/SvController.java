package edu.qh.SB_TruyenDuLieuSangView.controller;

import java.util.ArrayList;
import java.util.List;
import edu.qh.SB_TruyenDuLieuSangView.model.SinhVien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SvController 
{   
    @GetMapping("/SinhVien")
    public String Student(Model model) 
    {
        model.addAttribute("mssv", "64133129");
        model.addAttribute("hoten", "NGUYỄN LỮ QUỐC HƯNG");
        model.addAttribute("namsinh", 2004);
        model.addAttribute("gioitinh", "Nam");
        
        return "SinhVien";
    }
    
    private List<SinhVien> getData() {
        List<SinhVien> dsSinhVien = new ArrayList<>();
        dsSinhVien.add(new SinhVien("64000001", "Anh", 2004, "Nam"));
        dsSinhVien.add(new SinhVien("64000002", "Hai", 2004, "Nam"));
        dsSinhVien.add(new SinhVien("64000003", "Nhàn", 2004, "Nữ"));
        return dsSinhVien;
    }

    @GetMapping("/truyends")
    public String danhSachSinhVien(Model model) 
    {
        List<SinhVien> dsSinhVien = getData();
        model.addAttribute("dsSV", dsSinhVien);
        return "truyendsview";
    }
    
}
