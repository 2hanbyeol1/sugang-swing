package valueObject;

public class VLecture {
	private int id;
	private String name;
	private String professor;
	private int score;
	private String time;

	public int getId() { return id;	}
	public void setId(int id) {	this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getProfessor() { return professor; }
	public void setProfessor(String professor) { this.professor = professor; }
	
	public int getScore() { return score; }
	public void setScore(int score) { this.score = score; }
	
	public String getTime() { return time; }
	public void setTime(String time) { this.time = time; }
}
