package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Access(AccessType.PROPERTY)
public class Survival extends DomainEntity {

	//Constructors
	
	public Survival(){
		super();
	}
	
	// Attributes
	
	private String title;
	private String description;
	private Date moment;
	private Gps location;

	@NotBlank
	public String getTitle() {
		return title;

	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return description;

	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@Valid
	@NotNull
	public Gps getLocation() {
		return location;
	}

	public void setLocation(Gps location) {
		this.location = location;
	}
	
	// Relationships
	
	private Manager manager;
	private Trip trip;
	private Collection<Explorer> explorer;
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Valid
	@ManyToOne(optional=true)
	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	@Valid
	@ManyToMany
	public Collection<Explorer> getExplorer() {
		return explorer;
	}

	public void setExplorer(Collection<Explorer> explorer) {
		this.explorer = explorer;
	}
}
