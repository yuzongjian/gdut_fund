package model;

public class Item_track_add {

	private int id;

	@Override
	public String toString() {
		return "Item_track_add [id=" + id + ", old_title=" + old_title + ", old_evaluate=" + old_evaluate + ", time="
				+ time + ", content=" + content + ", sign=" + sign + ", track_id=" + track_id + "]";
	}

	private String old_title;
	private String old_evaluate;
	private String time;
	private String content;
	private String sign;
	private String track_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOld_title() {
		return old_title;
	}

	public void setOld_title(String old_title) {
		this.old_title = old_title;
	}

	public String getOld_evaluate() {
		return old_evaluate;
	}

	public void setOld_evaluate(String old_evaluate) {
		this.old_evaluate = old_evaluate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTrack_id() {
		return track_id;
	}

	public void setTrack_id(String track_id) {
		this.track_id = track_id;
	}

}
