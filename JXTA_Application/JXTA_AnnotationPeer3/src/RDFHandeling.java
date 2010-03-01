
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.DC_11;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author kashif
 */
public class RDFHandeling {

    public static void createRDF(Annotation ann, LinkedList queryTerm) throws SQLException, IOException {
        String[] ids = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String fileName = null;
        Model model = ModelFactory.createDefaultModel();
        model = ModelFactory.createDefaultModel();


        Blob testblob = ann.getDigitalSignatures();
        String cnvDS = HibernateActions.convertDStoBase64(testblob);
        
        
        fileName = ann.getFileLocation();
        Resource node = model.createResource("UUID"+ann.getAnnID()).addProperty(DC_11.creator, ann.getAnnotationAuthor()).addProperty(DC_11.date, ann.getCreationTime().toString()).addProperty(DC_11.publisher, cnvDS);

        int qtLength = queryTerm.size() / 2;
        //JOptionPane.showMessageDialog(null, "size of term length is "+ qtLength);
        int j = 0;
        for (int i = 0; i < qtLength; i++) {
            //System.out.println("No of for loop iterations "+ i);
            Property id = model.createProperty(ids[i]);
            Resource resource = model.createResource().addProperty(DC_11.description, queryTerm.get(j).toString()).addProperty(DC_11.language, queryTerm.get(j + 1).toString());
            model.add(node, id, resource);
            j=j+2;
        }
        //model.write( System.out, "N3" );

        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            model.write(fout, "N3");
        } catch (IOException e) {
            System.out.println("Exception caught" + e.getMessage());
        }

    }

    public static LinkedList readRDF(String fileName) {

        //System.out.println("going to read RDF...........................................");
        //String fileName = "C:\\test.n3";
        Model model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(fileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + fileName + " not found");
        }
        model.read(in, null, "N3");

        String uuid = null;
        String digSig = null;
        String timeStamp = null;
        String authorEmail = null;
       // String publickey = null;
//LinkedList queryTerm = new LinkedList();
        LinkedList concept = new LinkedList();
        LinkedList text = new LinkedList();
        int i = 0;
        int q = 0;
        int t = 0;
        int c = 0;
        StmtIterator iter = model.listStatements();
        LinkedList list = new LinkedList();
        while (iter.hasNext()) {
            Statement stmt = iter.next();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode node = stmt.getObject();

            if (node instanceof Resource) {
                System.out.println(" Object" + node.toString());
                //uuid = subject.getLocalName() +" "+ subject.getNameSpace()+ " "+ subject.getURI();
                String check = subject.getURI();
                uuid = check.substring(check.indexOf("UUID")+4);
                System.out.println("***************uuid in rdf is "+ uuid);
            } else {
                if (predicate.toString().endsWith("http://purl.org/dc/elements/1.1/publisher")) {
                    digSig = node.toString();
                    //System.out.println("Dig Sigs = "+ digSig);
                }
                if (predicate.toString().endsWith("http://purl.org/dc/elements/1.1/date")) {
                    timeStamp = node.toString();
                    //System.out.println("Time Stamp = " + timeStamp);
                }
                if (predicate.toString().endsWith("http://purl.org/dc/elements/1.1/creator")) {
                    authorEmail = node.toString();
                    //System.out.println("Creator = " + authorEmail);
                }
//                if (predicate.toString().endsWith("http://purl.org/dc/elements/1.1/rights")) {
//                    publickey = node.toString();
//                    //System.out.println("Creator = " + authorEmail);
//                }
                if (predicate.toString().endsWith("http://purl.org/dc/elements/1.1/language")) {
                    text.add(c, node);
                    c++;
                }
                if (predicate.toString().endsWith("http://purl.org/dc/elements/1.1/description")) {
                    concept.add(t, node);
                    t++;
                }
            }
        }



        list.add(uuid.trim());
        list.add(authorEmail.trim());
        list.add(timeStamp.trim());
        //list.add(annotates);
        list.add(digSig.trim());
        
       
        for (int l = 0; l < text.size(); l++) {
            //list.add(queryTerm.get(l).toString());
            //System.out.println("Query is = " + queryTerm.get(l).toString());
            list.add(concept.get(l).toString().trim());
            //System.out.println("Concept is = " + concept.get(l).toString());
            list.add(text.get(l).toString().trim());
            //System.out.println("Text is = " + text.get(l).toString());
        }

        System.out.println("uuid = " + list.get(0));
        System.out.println("creator = " + list.get(1));
        System.out.println("timestamp = " + list.get(2));
        System.out.println("digsig = " + list.get(3));
//        System.out.println("pub key = " + list.get(4));
        System.out.println("Concept is = " + concept.get(0).toString());
        System.out.println("text is = " + text.get(0).toString());
        System.out.println("Size of list after reading rdf is " + list.size());
//        for (int lm = 0; lm < list.size(); lm++) {
//            System.out.println("Item at index " + lm + " is " + list.get(lm));
//        }
        return list;
    }
}
