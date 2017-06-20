package commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Player")
@Table(name="PlayerEntities")
@SuppressWarnings("serial")
public class PlayerEntity implements Serializable {
	
	@Id
	@Column(name="playerName")
	protected String playerName;
	
	@OneToMany
	@JoinColumn(name= "playerName")
	protected List<GameSession> bestSessions; 
}
