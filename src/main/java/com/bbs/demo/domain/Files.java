package com.bbs.demo.domain;

public class Files {
	private int id;
	private int note_id;
	private String fileName;
	private byte[] files;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNote_id() {
		return note_id;
	}
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFiles() {
		return files;
	}
	public void setFiles(byte[] files) {
		this.files = files;
	}
}
