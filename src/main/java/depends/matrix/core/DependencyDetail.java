package depends.matrix.core;

public class DependencyDetail {

	private String src;
	private String dest;
	private String srcType;
	private String destType;

	public DependencyDetail(String src, String dest, String srcType, String destType) {
		this.src = src;
		this.dest = dest;
		this.srcType = srcType;
		this.destType = destType;
	}

	public String getSrc() {
		return src;
	}

	public String getDest() {
		return dest;
	}

	public String getDestType() {
		return destType;
	}

	public String getSrcType() {
		return srcType;
	}

	@Override
	public String toString() {
		return src + "(" + srcType + ")" + "->" + dest + "(" + destType + ")";
	}
}
