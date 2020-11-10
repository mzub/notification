package ru.geekbrains.entity.cian;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cian_regions", schema = "public")
public class CianRegion {
    private String cianCode;
    private String regionName;
    private String russianCode;

    @Basic
    @Column(name = "cian_code")
    public String getCianCode() {
        return cianCode;
    }

    public void setCianCode(String cianCode) {
        this.cianCode = cianCode;
    }

    @Basic
    @Column(name = "region_name")
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Id
    @Column(name = "russian_code")
    public String getRussianCode() {
        return russianCode;
    }

    public void setRussianCode(String russianCode) {
        this.russianCode = russianCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CianRegion that = (CianRegion) o;

        if (cianCode != null ? !cianCode.equals(that.cianCode) : that.cianCode != null) return false;
        if (regionName != null ? !regionName.equals(that.regionName) : that.regionName != null) return false;
        if (russianCode != null ? !russianCode.equals(that.russianCode) : that.russianCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cianCode != null ? cianCode.hashCode() : 0;
        result = 31 * result + (regionName != null ? regionName.hashCode() : 0);
        result = 31 * result + (russianCode != null ? russianCode.hashCode() : 0);
        return result;
    }
}
