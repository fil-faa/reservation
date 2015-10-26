package fr.emn.fil.reservation.model.dao.jpa;

/**
 * Created by Alexandre on 26/10/2015.
 */
public class JPAFilter {

    public enum FilterType {
        STRING, JOIN
    }

    private String field;

    private Object value;

    private FilterType type;

    private JPAFilter next;

    private JPAFilter(FilterType type, String field, Object value) {
        this.type = type;
        this.field = field;
        this.value = value;
    }

    public static JPAFilter create(FilterType type, String field, Object value) {
        return new JPAFilter(type, field, value);
    }


    public static JPAFilter create(JPAFilter filter) {
        return new JPAFilter(filter.type, filter.field, filter.value);
    }

    public JPAFilter add(FilterType type, String field, Object value) {
        JPAFilter filter = create(this);
        filter.next = create(type, field, value);
        return filter;
    }

    public String getQuery(String entity) {
        return this.getQuery(entity, true);
    }

    private String getQuery(String entity, boolean lastFilter) {
        String query;
        if(next == null)
            query = "SELECT f FROM " + entity + " f WHERE ";
        else query = next.getQuery(entity, false);

        switch(type) {
            case JOIN:
                if(value == null)
                    break;
                query += "f." + field + " = " + value + " AND ";
                break;

            case STRING:
                if(value == null)
                    break;
                query += "UPPER(f." + field + ") LIKE " + "'%" + value + "%' AND ";
        }

        if(lastFilter) {
            query += " 1 = 1";
        }
        return query;
    }


}
