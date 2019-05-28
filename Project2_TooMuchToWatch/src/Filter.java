
/** Casey Carr && Mun Young */

// This class is used to construct a list of Filter objects used for filtering the currentList
public class Filter {

	public String field; public String relation; public String target;

	public Filter(String field, String relation, String target) {
		this.field = field;
		this.relation = relation;
		this.target = target;
	}

	public String getField() {
		return field;
	}
	public String getRelation() {
		return relation;
	}
	public String getTarget() {
		return target;
	}
	public String toString() {
		String toReturn = field + " " + relation + " " + target;

		return toReturn;
	}
}
