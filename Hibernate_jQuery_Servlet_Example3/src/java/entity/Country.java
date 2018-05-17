package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "country")

public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "capital")
    private String capital;
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "area")
    private long area;
    
    @Column(name = "population")
    private long population;
    
    @Column(name = "lexpect")
    private BigDecimal lexpect;
    
    @Column(name = "gdp")
    private long gdp;
    
    @Column(name = "flag")
    private byte[] flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public BigDecimal getLexpect() {
        return lexpect;
    }

    public void setLexpect(BigDecimal lexpect) {
        this.lexpect = lexpect;
    }

    public long getGdp() {
        return gdp;
    }

    public void setGdp(long gdp) {
        this.gdp = gdp;
    }

    public byte[] getFlag() {
        return flag;
    }

    public void setFlag(byte[] flag) {
        this.flag = flag;
    }

    public Country() {
    }

    public Country(int id, String capital, String code, String name, long area, long population, BigDecimal lexpect, long gdp, byte[] flag) {
        this.id = id;
        this.capital = capital;
        this.code = code;
        this.name = name;
        this.area = area;
        this.population = population;
        this.lexpect = lexpect;
        this.gdp = gdp;
        this.flag = flag;
    }
    
}
