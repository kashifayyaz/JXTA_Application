





/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Indexed

public class AnnotationChild implements Serializable{
    @Id
    private String childId;
    @Field
    private String text;
    @Field
    private String concept;

    @ManyToOne(cascade=CascadeType.REMOVE)
    private Annotation annotation;


    protected AnnotationChild() {
    }

    public AnnotationChild(String id, String text, String concept) {
        this.childId = id;
        this.text = text;
        this.concept = concept;
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public String getChildId() {
        return childId;
    }
    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

}
