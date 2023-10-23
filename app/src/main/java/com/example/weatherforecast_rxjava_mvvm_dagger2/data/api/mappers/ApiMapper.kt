package com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.mappers

interface ApiMapper<Domain, Entity> {
    fun mapToDoMain(apiEntity: Entity): Domain
}