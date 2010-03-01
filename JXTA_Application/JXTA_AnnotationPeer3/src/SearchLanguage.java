


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.queryParser.ParseException;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kashif
 */
public class SearchLanguage {

    HibernateActions ha;

    SearchLanguage() throws NoSuchAlgorithmException, Exception {
        ha = new HibernateActions();
    }

    public List searchExecute(String inputQuery) throws IOException, ParseException, SQLException, java.text.ParseException {
        List<Annotation> results = null;
        StringTokenizer st = new StringTokenizer(inputQuery);
        String query = null;
        String field = null;
        
        if (inputQuery.contains("*") || inputQuery.contains("?")) {
            while (st.hasMoreTokens()) {
                field = st.nextToken("=").trim();
                query = st.nextToken("=").trim();
            }
            field = getField(field);
            results = ha.analyzeWildCardQuery(field, query);
        }
        
        else if (inputQuery.startsWith("phrase")) {
            String phraseQuery = inputQuery.substring(inputQuery.indexOf("phrase") + 7);
            StringTokenizer str = new StringTokenizer(phraseQuery);
            while (str.hasMoreTokens()) {
                field = str.nextToken("=").trim();
                query = str.nextToken("=").trim();
            }
            field = getField(field);
            System.out.println("Phrase language");
            results = ha.analyzePhraseQuery(field, query);
        }

        else if (inputQuery.startsWith("prefix")) {
            String phraseQuery = inputQuery.substring(inputQuery.indexOf("prefix") + 7);
            StringTokenizer str = new StringTokenizer(phraseQuery);
            while (str.hasMoreTokens()) {
                field = str.nextToken("=").trim();
                query = str.nextToken("=").trim();
            }
            field = getField(field);
            System.out.println("Prefix language");
            results = ha.analyzePrefixQuery(field, query);
        }

        else if (inputQuery.startsWith("term")) {
            String phraseQuery = inputQuery.substring(inputQuery.indexOf("term") + 5);
            StringTokenizer str = new StringTokenizer(phraseQuery);
            while (str.hasMoreTokens()) {
                field = str.nextToken("=").trim();
                query = str.nextToken("=").trim();
            }
            field = getField(field);
            System.out.println("term language");
            results = ha.analyzeTermQuery(field, query);
        }

        else if (inputQuery.startsWith("time")) {
            StringTokenizer str = new StringTokenizer(inputQuery);
            while (str.hasMoreTokens()) {
                field = str.nextToken("=");
                query = str.nextToken("=").trim();
            }
            field = "creationTime";
            if (inputQuery.contains("between")) {
                System.out.println("time multiple hai");
                StringTokenizer str1 = new StringTokenizer(query);
                String[] queries = new String[2];
                field = "creationTime";
                while (str1.hasMoreTokens()) {
                    queries[0] = str1.nextToken("between").trim();
                    queries[1] = str1.nextToken("between").trim();
                }
                System.out.println("field " + field);
                System.out.println("queries " + queries[0] + " and " + queries[1]);
                System.out.println("double time language");
                results = ha.analyzeRangeQuery(field, queries);
            }else {
                StringTokenizer str2 = new StringTokenizer(inputQuery);
                field = "creationTime";
                while (str2.hasMoreTokens()) {
                    query = str2.nextToken("=");
                }
                System.out.println("field " + field);
                System.out.println("query " + query);
                System.out.println("single time language");
                results = ha.analyzeSingleField(field, query);
            }
        }

        else if (inputQuery.contains(",")) {
            LinkedList lList = new LinkedList();
            System.out.println("space hai");
            while (st.hasMoreTokens()) {
                lList.add(st.nextToken(","));
            }
            for (int i = 0; i < lList.size(); i++) {
                System.out.println("list items " + lList.get(i));
            }
            String[] fields = new String[lList.size()];
            String[] queries = new String[lList.size()];
            for (int i = 0; i < lList.size(); i++) {
                StringTokenizer str = new StringTokenizer(lList.get(i).toString());
                while (str.hasMoreTokens()) {
                    fields[i] = str.nextToken("=").trim();
                    queries[i] = str.nextToken("=").trim();
                }
            }
            int result = 0;
            for (int i = 0; i < fields.length; i++) {
                fields[i] = getField(fields[i]);
                System.out.println(fields[i]);
                System.out.println(queries[i]);
            }
            
            System.out.println("multiple queries language");
            results = ha.analyzeMultipleFields(fields, queries);

           }

        else if(inputQuery.contains("=")){
            while (st.hasMoreTokens()) {
                field = st.nextToken("=").trim();
                query = st.nextToken("=").trim();
            }
            field = getField(field);
            try {
                System.out.println("bechari sigle field language and field is "+ field+" query is "+ query);
                results = ha.analyzeSingleField(field, query);
            } catch (IOException ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return results;
    }
  public List searchAnnotationID(String uuid) throws IOException, ParseException, SQLException {
        List<Annotation> results = null;
        results = ha.analyzeSingleField("annID", uuid);
        return results;
  }
    private String getField(String field){
        String newField = null;
            if (field.equals("author")) {
                newField = "annotationAuthor";
            } else if (field.equals("url")) {
                newField = "resourceURL";
            } else if (field.equals("description")) {
                newField = "description";
            }
            return newField;
    }
}
