package ru.geekbrains.entity.cian;

import javax.persistence.*;

@Entity
@Table(name = "russian_address_objects", schema = "public")
public class RussianAddressObject {
    private Integer actStatus;
    private String aoGuid;
    private String aoId;
    private Integer aoLevel;
    private String areaCode;
    private String autoCode;
    private Integer centStatuc;
    private String cityCode;
    private String code;
    private String formalName;
    private String nextId;
    private String offName;
    private String parentGuid;
    private String placeCode;
    private String plainCode;
    private String postalCode;
    private String prevId;
    private String regionCode;
    private String shortName;
    private String streetCode;
    private String updateTime;
    private String ctarCode;
    private String extrCode;
    private String sextCode;
    private Integer liveStatus;
    private String planCode;

    @Basic
    @Column(name = "act_status")
    public Integer getActStatus() {
        return actStatus;
    }

    public void setActStatus(Integer actStatus) {
        this.actStatus = actStatus;
    }

    @Basic
    @Id
    @Column(name = "ao_guid")
    public String getAoGuid() {
        return aoGuid;
    }

    public void setAoGuid(String aoGuid) {
        this.aoGuid = aoGuid;
    }

    @Basic
    @Column(name = "ao_id")
    public String getAoId() {
        return aoId;
    }

    public void setAoId(String aoId) {
        this.aoId = aoId;
    }

    @Basic
    @Column(name = "ao_level")
    public Integer getAoLevel() {
        return aoLevel;
    }

    public void setAoLevel(Integer aoLevel) {
        this.aoLevel = aoLevel;
    }

    @Basic
    @Column(name = "area_code")
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Basic
    @Column(name = "auto_code")
    public String getAutoCode() {
        return autoCode;
    }

    public void setAutoCode(String autoCode) {
        this.autoCode = autoCode;
    }

    @Basic
    @Column(name = "cent_statuc")
    public Integer getCentStatuc() {
        return centStatuc;
    }

    public void setCentStatuc(Integer centStatuc) {
        this.centStatuc = centStatuc;
    }

    @Basic
    @Column(name = "city_code")
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "formal_name")
    public String getFormalName() {
        return formalName;
    }

    public void setFormalName(String formalName) {
        this.formalName = formalName;
    }

    @Basic
    @Column(name = "next_id")
    public String getNextId() {
        return nextId;
    }

    public void setNextId(String nextId) {
        this.nextId = nextId;
    }

    @Basic
    @Column(name = "off_name")
    public String getOffName() {
        return offName;
    }

    public void setOffName(String offName) {
        this.offName = offName;
    }

    @Basic
    @Column(name = "parent_guid")
    public String getParentGuid() {
        return parentGuid;
    }

    public void setParentGuid(String parentGuid) {
        this.parentGuid = parentGuid;
    }

    @Basic
    @Column(name = "place_code")
    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    @Basic
    @Column(name = "plain_code")
    public String getPlainCode() {
        return plainCode;
    }

    public void setPlainCode(String plainCode) {
        this.plainCode = plainCode;
    }

    @Basic
    @Column(name = "postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "prev_id")
    public String getPrevId() {
        return prevId;
    }

    public void setPrevId(String prevId) {
        this.prevId = prevId;
    }

    @Basic
    @Column(name = "region_code")
    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    @Basic
    @Column(name = "short_name")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Basic
    @Column(name = "street_code")
    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    @Basic
    @Column(name = "update_time")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "ctar_code")
    public String getCtarCode() {
        return ctarCode;
    }

    public void setCtarCode(String ctarCode) {
        this.ctarCode = ctarCode;
    }

    @Basic
    @Column(name = "extr_code")
    public String getExtrCode() {
        return extrCode;
    }

    public void setExtrCode(String extrCode) {
        this.extrCode = extrCode;
    }

    @Basic
    @Column(name = "sext_code")
    public String getSextCode() {
        return sextCode;
    }

    public void setSextCode(String sextCode) {
        this.sextCode = sextCode;
    }

    @Basic
    @Column(name = "live_status")
    public Integer getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(Integer liveStatus) {
        this.liveStatus = liveStatus;
    }

    @Basic
    @Column(name = "plan_code")
    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RussianAddressObject that = (RussianAddressObject) o;

        if (actStatus != null ? !actStatus.equals(that.actStatus) : that.actStatus != null) return false;
        if (aoGuid != null ? !aoGuid.equals(that.aoGuid) : that.aoGuid != null) return false;
        if (aoId != null ? !aoId.equals(that.aoId) : that.aoId != null) return false;
        if (aoLevel != null ? !aoLevel.equals(that.aoLevel) : that.aoLevel != null) return false;
        if (areaCode != null ? !areaCode.equals(that.areaCode) : that.areaCode != null) return false;
        if (autoCode != null ? !autoCode.equals(that.autoCode) : that.autoCode != null) return false;
        if (centStatuc != null ? !centStatuc.equals(that.centStatuc) : that.centStatuc != null) return false;
        if (cityCode != null ? !cityCode.equals(that.cityCode) : that.cityCode != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (formalName != null ? !formalName.equals(that.formalName) : that.formalName != null) return false;
        if (nextId != null ? !nextId.equals(that.nextId) : that.nextId != null) return false;
        if (offName != null ? !offName.equals(that.offName) : that.offName != null) return false;
        if (parentGuid != null ? !parentGuid.equals(that.parentGuid) : that.parentGuid != null) return false;
        if (placeCode != null ? !placeCode.equals(that.placeCode) : that.placeCode != null) return false;
        if (plainCode != null ? !plainCode.equals(that.plainCode) : that.plainCode != null) return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;
        if (prevId != null ? !prevId.equals(that.prevId) : that.prevId != null) return false;
        if (regionCode != null ? !regionCode.equals(that.regionCode) : that.regionCode != null) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;
        if (streetCode != null ? !streetCode.equals(that.streetCode) : that.streetCode != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (ctarCode != null ? !ctarCode.equals(that.ctarCode) : that.ctarCode != null) return false;
        if (extrCode != null ? !extrCode.equals(that.extrCode) : that.extrCode != null) return false;
        if (sextCode != null ? !sextCode.equals(that.sextCode) : that.sextCode != null) return false;
        if (liveStatus != null ? !liveStatus.equals(that.liveStatus) : that.liveStatus != null) return false;
        if (planCode != null ? !planCode.equals(that.planCode) : that.planCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actStatus != null ? actStatus.hashCode() : 0;
        result = 31 * result + (aoGuid != null ? aoGuid.hashCode() : 0);
        result = 31 * result + (aoId != null ? aoId.hashCode() : 0);
        result = 31 * result + (aoLevel != null ? aoLevel.hashCode() : 0);
        result = 31 * result + (areaCode != null ? areaCode.hashCode() : 0);
        result = 31 * result + (autoCode != null ? autoCode.hashCode() : 0);
        result = 31 * result + (centStatuc != null ? centStatuc.hashCode() : 0);
        result = 31 * result + (cityCode != null ? cityCode.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (formalName != null ? formalName.hashCode() : 0);
        result = 31 * result + (nextId != null ? nextId.hashCode() : 0);
        result = 31 * result + (offName != null ? offName.hashCode() : 0);
        result = 31 * result + (parentGuid != null ? parentGuid.hashCode() : 0);
        result = 31 * result + (placeCode != null ? placeCode.hashCode() : 0);
        result = 31 * result + (plainCode != null ? plainCode.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (prevId != null ? prevId.hashCode() : 0);
        result = 31 * result + (regionCode != null ? regionCode.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (streetCode != null ? streetCode.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (ctarCode != null ? ctarCode.hashCode() : 0);
        result = 31 * result + (extrCode != null ? extrCode.hashCode() : 0);
        result = 31 * result + (sextCode != null ? sextCode.hashCode() : 0);
        result = 31 * result + (liveStatus != null ? liveStatus.hashCode() : 0);
        result = 31 * result + (planCode != null ? planCode.hashCode() : 0);
        return result;
    }
}
