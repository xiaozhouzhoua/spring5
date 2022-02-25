package com.demo.spring5.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MySQLRepository {
    @Autowired
    @Qualifier("newDatabaseClient")
    public DatabaseClient db;

    public List<String> findNames() {
        Flux<String> names = db.sql("select first_name, last_name from customers")
                .map(row -> row.get("first_name", String.class))
                .all();
        return names.toStream().collect(Collectors.toList());
    }
}
