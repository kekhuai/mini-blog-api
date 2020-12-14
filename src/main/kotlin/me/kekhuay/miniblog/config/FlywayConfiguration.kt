package me.kekhuay.miniblog.config

import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class FlywayConfiguration(datasource: DataSource) {
    init {
        Flyway.configure().baselineOnMigrate(true).dataSource(datasource).load().migrate()
    }
}