





/**
 *
 * @author kashif
 */
import javax.persistence.Id;
import org.hibernate.search.annotations.Field;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Set;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Indexed
public class Annotation implements Serializable {
    @Id
    @DocumentId
    private String annID;
    @Field
    private String resourceURL;
    @Field
    private String description;
   @Field(index = Index.UN_TOKENIZED, store=Store.NO)
    private Long creationTime;
    @Field
    private String annotationAuthor;
    @Field
    private boolean deprecated;

    private String fileLocation;
    
    private Blob digitalSignatures;

    private Set annotationTerms;

    protected Annotation() {
    }
    public Annotation(String id){
        this.annID = id;
    }
    public Annotation(String id, String name, String description, Long cTime, String annAuthor, boolean deprecated, String fileloc, Blob digsig) {
        this.annID = id;
        this.resourceURL = name;
        this.description = description;
        this.creationTime = cTime;
        this.annotationAuthor = annAuthor;
        this.deprecated = deprecated;
        this.fileLocation = fileloc;
        this.digitalSignatures = digsig;
    }

    public String getAnnID() {
        return annID;
    }

    public void setAnnID(String id) {
        this.annID = id;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String rURL) {
        this.resourceURL = rURL;
    }
    public String getDescription(){
        return description;
    }
    public  void setDescription(String description){
        this.description = description;
    }
    public Long getCreationTime(){
        return this.creationTime;
    }
    public void setCreationTime(Long cTime){
        this.creationTime = cTime;
    }
    public String getAnnotationAuthor(){
        return this.annotationAuthor;
    }
    public void setAnnotationAuthor(String annauthor){
        this.annotationAuthor = annauthor;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public String getFileLocation(){
        return this.fileLocation;
    }
    public void setFileLocation(String fileloc){
        this.fileLocation = fileloc;
    }
    public Blob getDigitalSignatures(){
        return this.digitalSignatures;
    }
    public void setDigitalSignatures(Blob digsig){
        this.digitalSignatures = digsig;
    }

    public Set getQueryData() {
        return annotationTerms;
    }

    public void setQueryData(Set queryData) {
        this.annotationTerms = queryData;
    }

}
