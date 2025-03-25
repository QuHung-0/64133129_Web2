package edu.qh.TongHopGK.model;

public class SinhVien
{
	private String mssv;
	private String hoTen;
	private double diemTB;


	public SinhVien(String mssv, String hoTen, double diemTB)
	{
		this.mssv = mssv;
		this.hoTen = hoTen;
		this.diemTB = diemTB;
	}

	public String getMssv()
	{
		return mssv;
	}

	public void setMssv(String mssv)
	{
		this.mssv = mssv;
	}

	public String getHoTen()
	{
		return hoTen;
	}

	public void setHoTen(String hoTen)
	{
		this.hoTen = hoTen;
	}

	public double getDiemTB()
	{
		return diemTB;
	}

	public void setDiemTB(double diemTB)
	{
		this.diemTB = diemTB;
	}
}
