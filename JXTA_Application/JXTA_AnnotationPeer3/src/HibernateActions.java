


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kashif
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RangeQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.search.Search;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;

public class HibernateActions {

    public static FullTextSession ftsess;
    //public SignatureTest signaturetest;
    public KeyPairGenerator kpg;
    public KeyPair kp;
    private static PrivateKey prvk = null;
    public static PublicKey pubk = null;
    RDFHandeling rdf = new RDFHandeling();
    SessionHandler sh = new SessionHandler();
    Session session = null;
    Transaction tx = null;
    SourceofUUID.UUID uuid = null;

    HibernateActions() throws NoSuchAlgorithmException, Exception {

        kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(512); // 512 is the keysize.
        kp = kpg.generateKeyPair();
        prvk = kp.getPrivate();

        pubk = kp.getPublic();
      
        session = sh.getSession();
        ftsess = indexing(session);

    }

    public void setUpDB() {
//        HibernateUtil.droptable("drop table annotation");
//        HibernateUtil.droptable("drop table annotation_child");
//        HibernateUtil.setup("create table annotation ( AnnID VARCHAR(50), ResourceURL VARCHAR(128), Description VARCHAR(256), CreationTime LONG VARCHAR, AnnotationAuthor VARCHAR(30) not null, FileLocation varchar(256) not null, DigitalSignatures blob not null)");
//        HibernateUtil.setup("create table annotation_child (AnnID VARCHAR(50), Text varchar(50),Concept  varchar(50),QueryTermID VARCHAR(50))");

    }

    public void populateAnnotationTable(LinkedList list, LinkedList queryTerm) throws Exception {
        try {
            tx = session.beginTransaction();
            uuid = new SourceofUUID.UUID();
            String struuid = uuid.toString();

            String resourceURL = list.get(0).toString();
            String description = list.get(1).toString();

            ///////////////////// Time Adjustument and saving//////////////////
            Date creationTime = new Date();
            System.out.println("time in date " + creationTime.toString());
            Long lCreationTime = creationTime.getTime();
            Long checkTime = lCreationTime / 1000;
            checkTime = checkTime * 1000;
            System.out.println("time in long " + lCreationTime);
            System.out.println("time in long after devision is " + checkTime);


            String annAuthor = list.get(2).toString();

            Annotation a1 = new Annotation();
            a1.setAnnID(struuid);
            a1.setResourceURL(resourceURL);
            a1.setDescription(description);
            a1.setCreationTime(checkTime);
            a1.setAnnotationAuthor(annAuthor);
            a1.setDeprecated(false);
            String annFileName = "RDF//"+a1.getAnnID()+".n3";//"C:\\RDFfiles/" + a1.getAnnID() + annAuthor.substring(0, annAuthor.indexOf('@')) + ".n3";
            
            a1.setFileLocation(annFileName);
/////////////////// Digital Signature Process///////////////////////////////
            String signatures = a1.getAnnID().trim()+a1.getCreationTime().toString().trim()+a1.getAnnotationAuthor().trim();
            System.out.println("Signatures at creation Time "+ signatures);
             Random random = new Random();
             int randomInt= random.nextInt(10000);
             String tempFileName = randomInt+"tempfile.txt";
             File file = new File(tempFileName);
             FileOutputStream fos = new FileOutputStream(file);
             fos.write(signatures.getBytes());// write the string in that file
             Blob blob = getDigSig(tempFileName);
             a1.setDigitalSignatures(blob);
             fos.close();
             System.out.println("file deletion status "+file.delete());
             System.out.println("Blob before encoding "+blob.toString());
             String cnvds = convertDStoBase64(blob);
             System.out.println("cnvds are "+ cnvds);
            

////////// populating second table///////////////////////////
            a1.setQueryData(new HashSet());
            int qtLength = queryTerm.size() / 2;
            int j = 0;
            for (int i = 0; i < qtLength; i++) {
                uuid = new SourceofUUID.UUID();
                String childUUID = uuid.toString();
                a1.getQueryData().add(new AnnotationChild(childUUID, queryTerm.get(j).toString(), queryTerm.get(j + 1).toString()));
                j = j + 2;
            }
//            String[] signatures1 = {a1.getAnnID()+"a", a1.getCreationTime().toString(), a1.getAnnotationAuthor()};
//            System.out.println("going to verify sigs");
//            verifyDigSig(testblob, signatures1);

            session.save(a1);
            tx.commit();
            ftsess = indexing(session);
            ////////////////////////create public key for remote shairing of Annotation
            RDFHandeling.createRDF(a1, queryTerm);
            String pkFileName = "RDF//"+a1.getAnnID()+".pk";
            File pkFile = new File(pkFileName);
            FileOutputStream pkfos = new FileOutputStream(pkFile);
            pkfos.write(getPublicKey());
            TestPeer.sendToPeers(annFileName, pkFileName);
            JOptionPane.showMessageDialog(null, "Saved and RDF Created at" + annFileName);

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
 public void populateviaRDF(LinkedList list, byte[] pk, PeerInterface gui, boolean searchResult) throws Exception{
            Annotation a1 = new Annotation();
        try {
            //session = sh.getSession();//it is invoked up in constructor
            tx = session.beginTransaction();

            System.out.println("size of list is "+ list.size());
            String struuid = list.get(0).toString().trim();
            String annAuthor = list.get(1).toString().trim();
            Long creationTime = Long.parseLong(list.get(2).toString().trim());
                     
            String resourceURL = " ";
            String description = " ";
            
            String cnvds = list.get(3).toString().trim();
            System.out.println("#######cnvds are "+ cnvds);
            String signatures = struuid.trim()+creationTime.toString().trim()+annAuthor.trim();
            System.out.println("Signatures at recieving Time "+ signatures);

             Random random = new Random();
             int randomInt= random.nextInt(10000);
             String tempFileName1 = randomInt+"tempfile1.txt";
             File file = new File(tempFileName1);
             Blob newblob = convertBase64toDS(cnvds);
             System.out.println("Blob after decoding "+newblob.toString());
             InputStream is = newblob.getBinaryStream();
             int size = is.available();
             byte[] newsigbytes = new byte[size];
             is.read(newsigbytes);
             FileOutputStream newfos = new FileOutputStream(file);
             newfos.write(signatures.getBytes());


             //byte[] publickey = null;////////////////////////////////////////////////////////////////
             X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(pk);
             KeyFactory keyFactory = KeyFactory.getInstance("DSA");
             PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
             //System.out.println("Public key finally is "+ pubKey.toString());

             boolean result = verifyDigSig(newsigbytes, tempFileName1, pubKey);
             System.out.println("Signature Verification Result = " + result);
             newfos.close();
             System.out.println("file deletion status "+file.delete());
                
             if(!result){
                 JOptionPane.showMessageDialog(null, "File recieved does not have valid Digital Signatures");
                 return;
             }
             else {

            a1.setAnnID(struuid);
            a1.setResourceURL(resourceURL);
            a1.setDescription(description);
            a1.setCreationTime(creationTime);
            a1.setAnnotationAuthor(annAuthor);
            a1.setDeprecated(false);
            a1.setDigitalSignatures(newblob);
            String annFileName = "RDF//"+a1.getAnnID()+".n3";
            a1.setFileLocation(annFileName);
           a1.setQueryData(new HashSet());

           //int qtLength = queryTerm.size() / 2;
           LinkedList queryTerm = new LinkedList();
            int j = 4;
            int count = (list.size()-4)/2;
            
            for (int i = 0; i < count; i++) {
                uuid = new SourceofUUID.UUID();
                String childUUID = uuid.toString();
                String text = list.get(j).toString();
                String concept = list.get(j+1).toString();
                a1.getQueryData().add(new AnnotationChild(childUUID, text, concept));
                queryTerm.add(text);
                queryTerm.add(concept);
                j = j + 2;

            }
            session.save(a1);
            tx.commit();
            ftsess = indexing(session);
            if(searchResult){
                    gui.addRowsInTables(a1);
            }
            RDFHandeling.createRDF(a1, queryTerm);
            String pkFileName = "RDF//"+a1.getAnnID()+".pk";
            File pkFile = new File(pkFileName);
            FileOutputStream pkfos = new FileOutputStream(pkFile);
            pkfos.write(getPublicKey());
            JOptionPane.showMessageDialog(null, "Saved and RDF Created at" + annFileName);

            JOptionPane.showMessageDialog(null, "Data has been read and saved in DB via RDF");
             }
        } catch ( HibernateException e ) {
            if ( tx != null ) tx.rollback();
            e.printStackTrace();
        }
        
    }
 public byte[] getPublicKey(){
     return pubk.getEncoded();
 }

    public void deleteAnnotation(String delUUID) throws IOException, ParseException, SQLException, NoSuchAlgorithmException, Exception {
        tx = session.beginTransaction();
        Annotation ann = new Annotation(delUUID);
        //showMessageDialog(null, "in delete "+ delUUID);
        ann = (Annotation) session.load(Annotation.class, ann.getAnnID()); //https://forums.hibernate.org/viewtopic.php?f=1&t=963103
        session.delete(ann);
        tx.commit();
        ftsess = indexing(session);
        
    }

    public void quit() {
    }

      public static Blob getDigSig(String fileName) throws Exception {
        byte[] sigbytes = SignatureTest.sign(fileName, prvk, "SHAwithDSA");
        java.sql.Blob blob = org.hibernate.Hibernate.createBlob(sigbytes);
        return blob;
    }
         public static boolean verifyDigSig(byte[] source, String fileName, PublicKey pubkey) throws SQLException, Exception {

             boolean result = SignatureTest.verify(fileName, pubkey, "SHAwithDSA", source);
            return result;
    }
  public static String convertDStoBase64(Blob ob) throws SQLException, IOException {
        Base64 bs = new Base64();
        InputStream x=ob.getBinaryStream();
        int size = x.available();
        byte b[]= new byte[size];
        x.read(b);
        byte[] dsbyte = bs.encode(b);
        String dsstr = new String(dsbyte);
        return dsstr;
    }

     public static Blob convertBase64toDS(String ob) throws SQLException {
        Base64 bs = new Base64();
        byte[] bstr = ob.getBytes();
        byte[] result = bs.decode(bstr);
        //System.out.println("cnvds after decoding " + new String(result));
        java.sql.Blob blob = org.hibernate.Hibernate.createBlob(result);
        return blob;
    }

    public static FullTextSession indexing(Session session) {
        List<Annotation> notes = session.createCriteria(Annotation.class).list();
        ftsess = Search.getFullTextSession(session);
        ftsess.getTransaction().begin();
        for (Annotation annot : notes) {
            ftsess.index(annot);
        }
        ftsess.getTransaction().commit();
        return ftsess;
    }

    public List analyzeSingleField(String fields, String key) throws IOException, ParseException, SQLException {
        List list = null;
        System.out.println("Analzying \"" + key + "\"");
        StandardAnalyzer analyzer = new StandardAnalyzer();
        TokenStream stream = analyzer.tokenStream("", new StringReader(key));
        QueryParser parser = new QueryParser(fields, new StandardAnalyzer());
        System.out.println("this is single field analyzer");
        org.apache.lucene.search.Query lquery = null;
        String test = null;
        org.apache.lucene.analysis.Token token = stream.next();
        System.out.println(token.termText());
        test = token.termText();
        lquery = parser.parse(test);
        list = search(lquery);

        return list;
    }

    public List analyzeMultipleFields(String[] fields, String[] queries) throws IOException, ParseException, SQLException {

        Query query = MultiFieldQueryParser.parse(queries, fields, new StandardAnalyzer());
        System.out.println("Query came is " + query.toString());
        List list = search(query);
        return list;
    }

    public List analyzeRangeQuery(String field, String[] rquery) throws ParseException, SQLException {
        Term lower = new Term(field, rquery[0]);
        Term upper = new Term(field, rquery[1]);
        RangeQuery query = new RangeQuery(lower, upper, true);
        //ConstantScoreRangeQuery query = new ConstantScoreRangeQuery(field, rquery[0],rquery[1], true, true);
        System.out.println("Range Query is " + query.toString());
        org.hibernate.search.FullTextQuery hibQuery = ftsess.createFullTextQuery(query, Annotation.class);
        List<Annotation> results = hibQuery.list();
        return results;
    }

    public List analyzeWildCardQuery(String field, String query1) throws SQLException {
        WildcardQuery query = new WildcardQuery(new Term(field, query1));
        System.out.println(query.toString());
        List list = search(query);
        return list;
    }

    public List analyzePhraseQuery(String field, String query1) throws SQLException {
        StringTokenizer st = new StringTokenizer(query1, " ");
        PhraseQuery query = new PhraseQuery();
        while (st.hasMoreTokens()) {
            query.add(new Term(field, st.nextToken()));
        }
        System.out.println("Phrase Query is  " + query.toString());
        List list = search(query);
        return list;
    }

    public List analyzePrefixQuery(String field, String query1) throws SQLException {
        PrefixQuery query = new PrefixQuery(new Term(field, query1));
        System.out.println("Prefix query is " + query.toString());
        List list = search(query);
        return list;
    }

    public List analyzeTermQuery(String field, String query1) throws SQLException {
        Term term = new Term(field, query1);
        TermQuery query = new TermQuery(term);
        System.out.println("term Query is " + query.toString());
        List list = search(query);
        return list;

    }

    public static List search(org.apache.lucene.search.Query lquery) throws SQLException {
        org.hibernate.search.FullTextQuery hquery = ftsess.createFullTextQuery(lquery, Annotation.class);
        System.out.println("search called");
        List results = hquery.list();
        return results;
    }
}
