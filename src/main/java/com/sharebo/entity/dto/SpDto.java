package com.sharebo.entity.dto;

import java.util.List;

public class SpDto {
	private String ch;
	private List<SpeciesortDto> spds;
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public List<SpeciesortDto> getSpds() {
		return spds;
	}
	public void setSpds(List<SpeciesortDto> spds) {
		this.spds = spds;
	}
}
