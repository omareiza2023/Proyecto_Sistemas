package com.corhuila.planillaescolar.controllers;

import com.corhuila.planillaescolar.entity.Persona;
import com.corhuila.planillaescolar.service.IPersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persona")
@CrossOrigin("*")
public class PersonaRestController {

    @Autowired
    private IPersonaService personaService;

    @Operation(summary = "Obtener todas las personas", description = "Retorna una lista de todas las personas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/get")
    public List<Persona> index() {
        return personaService.findAll();
    }

    @Operation(summary = "Obtener una persona por ID", description = "Retorna una persona dado su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persona encontrada"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada")
    })
    @GetMapping("/get/{id}")
    public Persona show(@PathVariable Long id) {
        return personaService.findById(id);
    }

    @Operation(summary = "Crear una nueva persona", description = "Crea una nueva persona en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Persona creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Persona create(@RequestBody Persona persona) {
        return personaService.save(persona);
    }

    @Operation(summary = "Actualizar una persona", description = "Actualiza la información de una persona existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persona actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada")
    })
    @PutMapping("/put/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Persona update(@RequestBody Persona persona, @PathVariable Long id) {
        return personaService.update(persona, id);
    }

    @Operation(summary = "Eliminar una persona", description = "Elimina una persona de la base de datos dado su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Persona eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada")
    })
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        personaService.delete(id);
    }
}
