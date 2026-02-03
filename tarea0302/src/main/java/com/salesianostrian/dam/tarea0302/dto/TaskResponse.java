package com.salesianostrian.dam.tarea0302.dto;

public record TaskResponse(
        Long id,
        String description,
        String ownerUsername
) {}