package com.example.stockapplication.domain.mappers

interface JoinEntitiesMapper<EntityFromFirst, EntityFromSecond, EntityTo> {
    fun map(entityFromFirst: EntityFromFirst, entityFromSecond: EntityFromSecond): EntityTo
}