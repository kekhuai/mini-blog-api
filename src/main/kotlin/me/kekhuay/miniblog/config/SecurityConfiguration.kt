package me.kekhuay.miniblog.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers("/h2-console/**")
    }
}