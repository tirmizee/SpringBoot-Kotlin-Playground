package com.tirmizee.repositories

import com.tirmizee.repositories.models.EmployeeDocument
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.update.UpdateRequest
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.reindex.UpdateByQueryRequest
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate
import org.springframework.data.elasticsearch.core.SearchHit
import org.springframework.data.elasticsearch.core.document.Document
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.data.elasticsearch.core.query.Query
import org.springframework.data.elasticsearch.core.query.UpdateQuery
import org.springframework.data.elasticsearch.core.query.UpdateResponse
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux


@Repository
class EmployeeRepository(
    private val reactiveElasticsearchTemplate: ReactiveElasticsearchTemplate
){

    fun findById(id: Int) :EmployeeDocument? {

        val queryBuilder: QueryBuilder = QueryBuilders
            .matchQuery(EmployeeDocument::id.name, id)

        val searchQuery: Query = NativeSearchQueryBuilder()
            .withQuery(queryBuilder)
            .build()

        val employeeHits: Flux<SearchHit<EmployeeDocument>> = reactiveElasticsearchTemplate
            .search(
                searchQuery,
                EmployeeDocument::class.java
            )

        return employeeHits.blockFirst()?.content ?: null

    }

    fun findByName(name: String): EmployeeDocument? {

        val queryBuilder: QueryBuilder = QueryBuilders
            .matchQuery(EmployeeDocument::name.name, name)

        val searchQuery: Query = NativeSearchQueryBuilder()
            .withQuery(queryBuilder)
            .build()

        val employeeHits: Flux<SearchHit<EmployeeDocument>> = reactiveElasticsearchTemplate
            .search(
                searchQuery,
                EmployeeDocument::class.java
            )

        return employeeHits.blockFirst()?.content ?: null

    }

    fun findByUsername(username: String): EmployeeDocument? {

        val queryBuilder: QueryBuilder = QueryBuilders
            .matchQuery(EmployeeDocument::username.name, username)

        val searchQuery: Query = NativeSearchQueryBuilder()
            .withQuery(queryBuilder)
            .build()

        val employeeHits: Flux<SearchHit<EmployeeDocument>> = reactiveElasticsearchTemplate
            .search(
                searchQuery,
                EmployeeDocument::class.java
            )

        return employeeHits.blockFirst()?.content ?: null

    }

    fun updateAge(id: String, age: Int): UpdateResponse? {

        val document: Document = Document.create().apply {
            this[EmployeeDocument::age.name] = age
        }

        val updateQuery = UpdateQuery.builder(id)
            .withDocument(document)
            .build()

        return reactiveElasticsearchTemplate.update(updateQuery, IndexCoordinates.of(EmployeeDocument.index)).block()

    }


}