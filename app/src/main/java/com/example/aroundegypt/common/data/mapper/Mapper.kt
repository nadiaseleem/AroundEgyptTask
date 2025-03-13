package com.example.aroundegypt.common.data.mapper

abstract class Mapper<Dto, Domain, Entity> {
    abstract fun dtoToDomain(model: Dto): Domain

    abstract fun entityToDomain(model: Entity): Domain

    abstract fun domainToEntity(model: Domain): Entity
}