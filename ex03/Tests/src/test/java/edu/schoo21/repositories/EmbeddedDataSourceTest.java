package edu.schoo21.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;

public class EmbeddedDataSourceTest {
    private EmbeddedDatabase embeddedDatabase;

    @BeforeEach
    public void init() {
        embeddedDatabase = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("schema.sql").addScript("data.sql").build();
    }

    @Test
    public void testEmbeddedDataSource() throws SQLException {
        Assertions.assertNotNull(embeddedDatabase.getConnection());
    }
}
