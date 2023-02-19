package com.example.data.mappers

interface Mapper<T, R> {

    fun toDomainModel(dto: T): R
}