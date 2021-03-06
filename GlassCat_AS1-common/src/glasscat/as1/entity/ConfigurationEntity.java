/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sun Yeqing
 */
@Entity
@Table(name = "as1_config")
@NamedQueries({
    @NamedQuery(name = "findByTypeAndName", query = "select c from ConfigurationEntity c where c.configType=:type AND c.configName=:name")})
public class ConfigurationEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "config_id")
    private String id;
    @Column(name = "type", length = 255, nullable = false)
    private String configType;
    @Column(name = "config_name", length = 255, nullable = false)
    private String configName;
    @Column(name = "config_value", length = 255, nullable = false)
    private String configValue;

    public ConfigurationEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfigurationEntity)) {
            return false;
        }
        ConfigurationEntity other = (ConfigurationEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "glasscat.as1.entity.ConfigurationEntity[ id=" + id + " ]";
    }
    
}
