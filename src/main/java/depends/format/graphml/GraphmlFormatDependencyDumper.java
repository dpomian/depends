package depends.format.graphml;

import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import depends.format.AbstractFormatDependencyDumper;
import depends.format.FileAttributes;
import depends.matrix.core.DependencyMatrix;

public class GraphmlFormatDependencyDumper extends AbstractFormatDependencyDumper {

    @Override
    public String getFormatName() {
        return "graphml";
    }

    public GraphmlFormatDependencyDumper(DependencyMatrix matrix, String projectName, String outputDir) {
        super(matrix, projectName, outputDir);
    }

    @Override
    public boolean output() {
        GBuilder gBuilder = new GBuilderCoarse();
        GDepObject gRoot = gBuilder.build(matrix, new FileAttributes(name));
        toXml(gRoot, composeFilename() + "." + getFormatName());
        return true;
    }

    private void toXml(GDepObject gRoot, String graphmlFileName)  {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GDepObject.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(gRoot, new FileOutputStream(graphmlFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}