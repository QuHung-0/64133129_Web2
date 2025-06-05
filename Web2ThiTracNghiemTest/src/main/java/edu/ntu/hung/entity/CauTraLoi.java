package edu.ntu.hung.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CauTraLoi")
public class CauTraLoi
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CauTraLoi_ID")
	private Integer cauTraLoiId;

	// Khóa ngoại Many-to-One nối đến CauHoi
	@ManyToOne
	@JoinColumn(name = "CauHoi_ID", nullable = false)
	private CauHoi cauHoi; // Biến Java kiểu CauHoi

	@Column(name = "CauTraLoi", nullable = false)
	private String noiDungTraLoi;

	@Column(name = "Dung", nullable = false)
	private Boolean dung; // true nếu đáp án đúng, false nếu sai

	// ===== Constructor mặc định =====
	public CauTraLoi()
	{
	}

	// Constructor nhanh
	public CauTraLoi(CauHoi cauHoi, String noiDungTraLoi, Boolean dung)
	{
		this.cauHoi = cauHoi;
		this.noiDungTraLoi = noiDungTraLoi;
		this.dung = dung;
	}

	// ===== Getter & Setter =====
	public Integer getCauTraLoiId()
	{
		return cauTraLoiId;
	}

	public void setCauTraLoiId(Integer cauTraLoiId)
	{
		this.cauTraLoiId = cauTraLoiId;
	}

	public CauHoi getCauHoi()
	{
		return cauHoi;
	}

	public void setCauHoi(CauHoi cauHoi)
	{
		this.cauHoi = cauHoi;
	}

	public String getNoiDungTraLoi()
	{
		return noiDungTraLoi;
	}

	public void setNoiDungTraLoi(String noiDungTraLoi)
	{
		this.noiDungTraLoi = noiDungTraLoi;
	}

	public Boolean getDung()
	{
		return dung;
	}

	public void setDung(Boolean dung)
	{
		this.dung = dung;
	}
}
