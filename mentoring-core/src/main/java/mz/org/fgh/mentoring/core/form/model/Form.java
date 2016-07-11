package mz.org.fgh.mentoring.core.form.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Entity
@Table(name = "FORMS", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
public class Form {

}
