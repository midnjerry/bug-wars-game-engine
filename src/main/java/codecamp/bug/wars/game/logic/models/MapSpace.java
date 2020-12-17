package codecamp.bug.wars.game.logic.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

public enum MapSpace {

    OPEN, WALL;

}
