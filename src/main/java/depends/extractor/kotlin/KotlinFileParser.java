package depends.extractor.kotlin;

import depends.entity.repo.EntityRepo;
import depends.extractor.FileParser;
import depends.relations.IBindingResolver;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class KotlinFileParser implements FileParser {

	@Override
	public void parse() throws IOException {
	       CharStream input = CharStreams.fromFileName(fileFullPath);
	        Lexer lexer = new KotlinLexer(input);
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        KotlinParser parser = new KotlinParser(tokens);
	        KotlinListener bridge = new KotlinListener(fileFullPath, entityRepo, bindingResolver);
		    ParseTreeWalker walker = new ParseTreeWalker();
		    walker.walk(bridge, parser.kotlinFile());
	}
	
	private String fileFullPath;
	private EntityRepo entityRepo;
	private IBindingResolver bindingResolver;
	public KotlinFileParser(String fileFullPath,EntityRepo entityRepo, IBindingResolver bindingResolver) {
        this.fileFullPath = fileFullPath;
        this.entityRepo = entityRepo;
        this.bindingResolver = bindingResolver;
	}


}
