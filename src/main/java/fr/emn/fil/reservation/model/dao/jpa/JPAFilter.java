package fr.emn.fil.reservation.model.dao.jpa;

/**
 * Created by Alexandre on 26/10/2015.
 */
public class JPAFilter {

    public enum FilterType {
        STRING, JOIN
    }

    private Long order;

    private String field;

    private Object value;

    private FilterType type;

    private JPAFilter next;

    private JPAFilter(FilterType type, String field, Object value, Long order) {
        this.type = type;
        this.field = field;
        this.value = value;
        this.order = order;
    }

    private JPAFilter(FilterType type, String field, Object value) {
        this(type, field, value, 0L);
    }

    public static JPAFilter create(FilterType type, String field, Object value) {
        return new JPAFilter(type, field, value);
    }

    public static JPAFilter create(FilterType type, String field, Object value, Long order) {
        return new JPAFilter(type, field, value, order);
    }

    public static JPAFilter create(JPAFilter filter) {
        return new JPAFilter(filter.type, filter.field, filter.value, filter.order);
    }

    public JPAFilter add(FilterType type, String field, Object value) {
        JPAFilter filter = create(this);
        filter.next = create(type, field, value, this.order + 1);
        return filter;
    }

    public String getQuery(String entity) {
        return this.getQuery(entity, true);
    }

    private String getQuery(String entity, boolean lastFilter) {
        String query;
        if(next == null)
            query = "SELECT f FROM " + entity + " WHERE ";
        else query = next.getQuery(entity, false);

        switch(type) {
            case JOIN:
                if(value == null)
                    break;
                query = field + " = " + value + " AND";
                break;

            case STRING:
                query = "UPPER(" + field + ") LIKE " + "'%" + value + "%' AND";
        }

        if(lastFilter) {
            query = " 1 = 1";
        }
        return query;
    }


}
