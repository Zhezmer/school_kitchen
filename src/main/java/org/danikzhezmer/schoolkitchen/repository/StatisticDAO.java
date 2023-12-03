package org.danikzhezmer.schoolkitchen.repository;

import org.danikzhezmer.schoolkitchen.entity.Statistic;
import org.intellij.lang.annotations.Language;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StatisticDAO {

    @Language("SQL")
    private final static String STATISTIC_QUERY = """
            SELECT sg.name as group,
                   p.name as product,
                   sum(koi.qty) as total_quantity,
                   koi.measure
            FROM kitchen_order ko
            JOIN kitchen_order_item koi on ko.id = koi.kitchen_order_id
            join product p on p.id = koi.product_id
            join school_group sg on sg.id = ko.group_id
            WHERE ko.creation_date >= ?
              and ko.creation_date <= ?
              and sg.name = ?
              and p.name in (%s)
            group by sg.name, p.name, koi.measure""";
    private final JdbcTemplate jdbcTemplate;

    public StatisticDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Statistic> getStat(String groupName,
                                   LocalDate dateFrom,
                                   LocalDate dateTo,
                                   List<String> productNames) {
        final var productNamesFilter = productNames.stream()
                .map(s -> "'" + s + "'")
                .collect(Collectors.joining(","));
        return jdbcTemplate.query(
              String.format(STATISTIC_QUERY, productNamesFilter),
                new Object[]{dateFrom, dateTo, groupName},
                (rs, rowNum) -> new Statistic(
                        rs.getString("group"),
                        rs.getString("product"),
                        rs.getInt("total_quantity"),
                        rs.getString("measure")
                ));
    }
}
