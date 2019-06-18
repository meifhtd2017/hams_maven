package com.metime.dir.vo;

import java.util.List;

public class DirVo {
	
	private Integer dirId;
	private Integer fDirId;
	private String dirName;
	private List<DirVo> dirList;
	public Integer getDirId() {
		return dirId;
	}
	public void setDirId(Integer dirId) {
		this.dirId = dirId;
	}
	public Integer getfDirId() {
		return fDirId;
	}
	public void setfDirId(Integer fDirId) {
		this.fDirId = fDirId;
	}
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	public List<DirVo> getDirList() {
		return dirList;
	}
	public void setDirList(List<DirVo> dirList) {
		this.dirList = dirList;
	}
}
