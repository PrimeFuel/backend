package com.primefuel.fulltank.platform.shared.infrastructure.persistence.jpa.configuration;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class MySqlSchemaCompatibilityInitializer {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public MySqlSchemaCompatibilityInitializer(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void updateLegacyEnumColumns() throws SQLException {
        try (var connection = dataSource.getConnection()) {
            if (!connection.getMetaData().getDatabaseProductName().toLowerCase().contains("mysql")) {
                return;
            }
        }
        jdbcTemplate.execute("ALTER TABLE fuel_orders MODIFY COLUMN status VARCHAR(30) NOT NULL");
        jdbcTemplate.execute("ALTER TABLE notifications MODIFY COLUMN type VARCHAR(40) NOT NULL");
    }
}
