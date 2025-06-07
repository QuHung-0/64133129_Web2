package edu.ntu.hung.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MonHoc")
public class MonHoc
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Cột trong DB là `MonHoc_ID`
	@Column(name = "MonHoc_ID")
	private Integer monHocId; 
	
	@Column(name = "TenMonHoc", nullable = false)
	private String tenMonHoc; 
	// ===== Constructor mặc định =====
	public MonHoc()
	{
	}

	// Constructor nhanh để tạo đối tượng (dùng khi cần)
	public MonHoc(String tenMonHoc)
	{
		this.tenMonHoc = tenMonHoc;
	}

	// ===== Getter & Setter =====
	public Integer getMonHocId()
	{
		return monHocId;
	}

	public void setMonHocId(Integer monHocId)
	{
		this.monHocId = monHocId;
	}

	public String getTenMonHoc()
	{
		return tenMonHoc;
	}

	public void setTenMonHoc(String tenMonHoc)
	{
		this.tenMonHoc = tenMonHoc;
	}
}
