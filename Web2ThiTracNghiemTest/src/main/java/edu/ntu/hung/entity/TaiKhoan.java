package edu.ntu.hung.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 1) Bổ sung @Column(name = "TaiKhoan_ID") để Hibernate biết đúng cột
	@Column(name = "TaiKhoan_ID")
	private Integer taiKhoanId;

	@Column(name = "TenDangNhap", nullable = false, unique = true)
	private String tenDangNhap;

	@Column(name = "MatKhau", nullable = false)
	private String matKhau;

	@Column(name = "VaiTro", nullable = false)
	private String vaiTro; // Giá trị 'GIAO_VIEN' hoặc 'HOC_SINH'

	@Column(name = "HoTen", nullable = false)
	private String hoTen;

	// ======= Constructor =======
	public TaiKhoan()
	{
	}

	public TaiKhoan(String tenDangNhap, String matKhau, String vaiTro, String hoTen)
	{
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.vaiTro = vaiTro;
		this.hoTen = hoTen;
	}

	// ======= Getter & Setter =======
	public Integer getTaiKhoanId()
	{
		return taiKhoanId;
	}

	public void setTaiKhoanId(Integer taiKhoanId)
	{
		this.taiKhoanId = taiKhoanId;
	}

	public String getTenDangNhap()
	{
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap)
	{
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau()
	{
		return matKhau;
	}

	public void setMatKhau(String matKhau)
	{
		this.matKhau = matKhau;
	}

	public String getVaiTro()
	{
		return vaiTro;
	}

	public void setVaiTro(String vaiTro)
	{
		this.vaiTro = vaiTro;
	}

	public String getHoTen()
	{
		return hoTen;
	}

	public void setHoTen(String hoTen)
	{
		this.hoTen = hoTen;
	}
}
