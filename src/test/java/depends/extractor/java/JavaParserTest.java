package depends.extractor.java;

import depends.extractor.ParserTest;

public abstract class JavaParserTest  extends ParserTest{
	public void init() {
		langProcessor = new JavaProcessor();
		super.init();
	}
	
	public JavaFileParser createParser(String src) {
		return new JavaFileParser(src,entityRepo, bindingResolver);
	}
}
