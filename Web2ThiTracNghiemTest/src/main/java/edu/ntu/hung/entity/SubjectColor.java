package edu.ntu.hung.entity;

public class SubjectColor
{
	private MonHoc monHoc;
	private String colorClass;

	public SubjectColor(MonHoc monHoc, String colorClass)
	{
		this.monHoc = monHoc;
		this.colorClass = colorClass;
	}

	public MonHoc getMonHoc()
	{
		return monHoc;
	}

	public void setMonHoc(MonHoc monHoc)
	{
		this.monHoc = monHoc;
	}

	public String getColorClass()
	{
		return colorClass;
	}

	public void setColorClass(String colorClass)
	{
		this.colorClass = colorClass;
	}
}
