package edu.ntu.hung.entity;

/**
 * Mỗi đối tượng QuizResultItem chứa: - câu hỏi (CauHoi) - nội dung đáp án học
 * sinh chọn (String) - nội dung đáp án đúng (String) - đúng/sai (boolean)
 */
public class QuizResultItem
{
	private CauHoi cauHoi;
	private String dapAnDaChon;
	private String dapAnDung;
	private boolean dung;

	// Constructor
	public QuizResultItem(CauHoi cauHoi, String dapAnDaChon, String dapAnDung, boolean dung)
	{
		this.cauHoi = cauHoi;
		this.dapAnDaChon = dapAnDaChon;
		this.dapAnDung = dapAnDung;
		this.dung = dung;
	}

	// Getter và Setter
	public CauHoi getCauHoi()
	{
		return cauHoi;
	}

	public void setCauHoi(CauHoi cauHoi)
	{
		this.cauHoi = cauHoi;
	}

	public String getDapAnDaChon()
	{
		return dapAnDaChon;
	}

	public void setDapAnDaChon(String dapAnDaChon)
	{
		this.dapAnDaChon = dapAnDaChon;
	}

	public String getDapAnDung()
	{
		return dapAnDung;
	}

	public void setDapAnDung(String dapAnDung)
	{
		this.dapAnDung = dapAnDung;
	}

	public boolean isDung()
	{
		return dung;
	}

	public void setDung(boolean dung)
	{
		this.dung = dung;
	}
}
