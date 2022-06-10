package depends.matrix.core;

import depends.entity.Entity;

public class DependencyDetail {
	private Entity srcEntity;
	private Entity destEntity;
	private LocationInfo srcLocation;
	private LocationInfo destLocation;

	public DependencyDetail(Entity srcEntity, Entity destEntity, LocationInfo srcLocation, LocationInfo destLocation) {
		this.srcEntity = srcEntity;
		this.destEntity = destEntity;
		this.srcLocation = srcLocation;
		this.destLocation = destLocation;
	}

	@Override
	public String toString() {
		return srcLocation + "->" + destLocation;
	}

	public Entity getSrcEntity() {
		return srcEntity;
	}

	public Entity getDestEntity() {
		return destEntity;
	}

	public LocationInfo getSrcLocation() {
		return srcLocation;
	}

	public LocationInfo getDestLocation() {
		return destLocation;
	}
}
