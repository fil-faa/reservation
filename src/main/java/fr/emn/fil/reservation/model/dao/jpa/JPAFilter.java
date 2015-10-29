package fr.emn.fil.reservation.model.dao.jpa;

/**
 * Composite class giving cumulative filters on several database fields
 * Created by Alexandre on 26/10/2015.
 */
public class JPAFilter {

    /**
     * Fields supported are strings (with a SQL LIKE), and joins
     */
    public enum FilterType {
        STRING, JOIN
    }

    /**
     * Attribute of the entity to filter
     */
    private String field;

    /**
     * <code>null</code> if we do not want to filter with it
     */
    private Object value;

    private FilterType type;

    /**
     * Next filter to apply
     */
    private JPAFilter next;

    private JPAFilter(FilterType type, String field, Object value, JPAFilter next) {
        this.type = type;
        this.field = field;
        this.value = value;
        this.next = next;
    }

    public static JPAFilter create(FilterType type, String field, Object value, JPAFilter next) {
        return new JPAFilter(type, field, value, next);
    }

    public static JPAFilter create(FilterType type, String field, Object value) {
        return new JPAFilter(type, field, value, null);
    }


    public static JPAFilter create(JPAFilter filter) {
        return new JPAFilter(filter.type, filter.field, filter.value, filter.next);
    }

    public JPAFilter add(FilterType type, String field, Object value) {
        JPAFilter filter = create(type, field, value);
        filter.next = create(this);
        return filter;
    }

    /**
     * It permits to get the final SQL query, by finally giving an entity name
     * @param entity name of the JPA entity
     * @return a valid SQL request
     */
    public String getQuery(String entity) {
        return this.getQuery(entity, true);
    }

    /**
     * (Implementation) It permits to get the final SQL query, by finally giving an entity name
     * @param entity name of the JPA entity
     * @param lastFilter <code>true</code> if this filter is the last executed
     * @return a valid SQL request
     */
    private String getQuery(String entity, boolean lastFilter) {
        String query;
        if(next == null)
            query = "SELECT f FROM " + entity + " f WHERE "; // we initialize the SQL quuery with an entity name
        else query = next.getQuery(entity, false);

        switch(type) {
            case JOIN:
                if(value == null)
                    break;
                query += "f." + field + " = " + value + " AND ";
                break;

            case STRING: // string fields are matched by a SQL like (insensitive to case)
                if(value == null)
                    break;
                query += "UPPER(f." + field + ") LIKE " + "'%" + value.toString().toUpperCase() + "%' AND ";
        }

        if(lastFilter) { // a last query (always true) to have something after the AND
            query += " 1 = 1";
        }
        return query;
    }


}
