package chap2;

import java.util.List;

public class Project {
	private List<String> srcdirs;
	private String bindir;
	private BuilderRunner b;
	private String classpath;
	public void build() {
		b.build(srcdirs,bindir);
	}
	
	//getter, setter
	public List<String> getSrcdirs() {
		return srcdirs;
	}
	public void setSrcdirs(List<String> srcdirs) {
		this.srcdirs = srcdirs;
	}
	public String getBindir() {
		return bindir;
	}
	public void setBindir(String bindir) {
		this.bindir = bindir;
	}
	public BuilderRunner getB() {
		return b;
	}
	public void setB(BuilderRunner b) {
		this.b = b;
	}
	public String getClasspath() {
		return classpath;
	}
	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}
	
}
