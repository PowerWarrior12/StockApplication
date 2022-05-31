package com.example.stockapplication.domain.mappers

interface EntityMapper<EntityFrom, EntityTo> {
    fun map(entityFrom: EntityFrom): EntityTo
}