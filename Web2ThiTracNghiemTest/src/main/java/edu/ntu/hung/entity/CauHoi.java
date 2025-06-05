package edu.ntu.hung.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CauHoi")
public class CauHoi
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CauHoi_ID")
	private Integer cauHoiId;

	// Đây là khóa ngoại (Many-to-One) nối đến MonHoc
	@ManyToOne
	@JoinColumn(name = "MonHoc_ID", nullable = false)
	private MonHoc monHoc; // biến Java kiểu MonHoc để Hibernate biết liên kết

	@Column(name = "CauHoi", columnDefinition = "TEXT", nullable = false)
	private String noiDungCauHoi;

	// ===== Constructor mặc định =====
	public CauHoi()
	{
	}

	// Constructor nhanh để khởi tạo
	public CauHoi(MonHoc monHoc, String noiDungCauHoi)
	{
		this.monHoc = monHoc;
		this.noiDungCauHoi = noiDungCauHoi;
	}

	// ===== Getter & Setter =====
	public Integer getCauHoiId()
	{
		return cauHoiId;
	}

	public void setCauHoiId(Integer cauHoiId)
	{
		this.cauHoiId = cauHoiId;
	}

	public MonHoc getMonHoc()
	{
		return monHoc;
	}

	public void setMonHoc(MonHoc monHoc)
	{
		this.monHoc = monHoc;
	}

	public String getNoiDungCauHoi()
	{
		return noiDungCauHoi;
	}

	public void setNoiDungCauHoi(String noiDungCauHoi)
	{
		this.noiDungCauHoi = noiDungCauHoi;
	}
}
