package thiGK.ntu64133129.model;

public class Topic
{
	private int id;
	private String topicName;
	private String topicDescription;
	private String supervisorId;
	private String topicType;

	public Topic(int id, String topicName, String topicDescription, String supervisorId, String topicType)
	{
		this.id = id;
		this.topicName = topicName;
		this.topicDescription = topicDescription;
		this.supervisorId = supervisorId;
		this.topicType = topicType;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTopicName()
	{
		return topicName;
	}

	public void setTopicName(String topicName)
	{
		this.topicName = topicName;
	}

	public String getTopicDescription()
	{
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription)
	{
		this.topicDescription = topicDescription;
	}

	public String getSupervisorId()
	{
		return supervisorId;
	}

	public void setSupervisorId(String supervisorId)
	{
		this.supervisorId = supervisorId;
	}

	public String getTopicType()
	{
		return topicType;
	}

	public void setTopicType(String topicType)
	{
		this.topicType = topicType;
	}
}
