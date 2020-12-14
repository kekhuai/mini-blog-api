package me.kekhuay.miniblog.config

import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class FlywayConfig(datasource: DataSource) {
    init {
        Flyway.configure()
            .baselineOnMigrate(true)
            .dataSource(datasource)
            .load()
            .migrate()
    }
}
