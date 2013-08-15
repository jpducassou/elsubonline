package uy.com.elsubonline.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="auctions")
public class Auction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String short_description;
    private String long_description;
    private double base_price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creation_time;

    @Temporal(TemporalType.TIMESTAMP)
    private Date closing_time;

    @ManyToOne
    private Category category;

    public Auction() {
        // Empty constructor
    }

    public Auction(String title, String short_description, String long_description, double base_price, Date closing_time, Category category) {

        // Basic attibutes
        this.title             = title;
        this.short_description = short_description;
        this.long_description  = long_description;
        this.base_price        = base_price;
        this.closing_time      = closing_time;
        this.category          = category;

        // Calculated attributes
        this.creation_time = new Date();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    public double getBase_price() {
        return base_price;
    }

    public void setBase_price(double base_price) {
        this.base_price = base_price;
    }

    public Date getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Date creation_time) {
        this.creation_time = creation_time;
    }

    public Date getClosing_time() {
        return closing_time;
    }

    public void setClosing_time(Date closing_time) {
        this.closing_time = closing_time;
    }
    
    @Override
    public String toString() {
        return "uy.com.elsubonline.domain.Auction[ id=" + getId() + " ]";
    }

}
