package mz.org.fgh.mentoring.core.setting.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.util.LocalDateTimeAdapter;

/***
 * 
 * @author Jose Julai Ritsure
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "SETTINGS", uniqueConstraints = @UniqueConstraint(columnNames = { "DESIGNATION" }))
public class Setting extends GenericEntity {

	private static final long serialVersionUID = -2342276325516694216L;

	@NotNull
	@Column(name = "DESIGNATION", nullable = false)
	private String designation;

	@NotNull
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@Column(name = "SETTING_VALUE", nullable = false)
	private LocalDateTime value;

	@NotNull
	@Column(name = "DESCRIPTION")
	private String description;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDateTime getValue() {
		return value;
	}

	public void setValue(LocalDateTime value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, designation, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setting other = (Setting) obj;
		return Objects.equals(description, other.description) && Objects.equals(designation, other.designation)
				&& Objects.equals(value, other.value);
	}
}
